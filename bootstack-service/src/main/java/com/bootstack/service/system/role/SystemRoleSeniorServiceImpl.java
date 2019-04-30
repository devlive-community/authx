/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bootstack.service.system.role;

import com.bootstack.model.system.menu.SystemMenuModel;
import com.bootstack.model.system.menu.SystemMenuTypeModel;
import com.bootstack.model.system.role.SysteMenuTreeItemModel;
import com.bootstack.model.system.role.SystemMenuTreeModel;
import com.bootstack.model.system.role.SystemRoleModel;
import com.bootstack.service.system.menu.SystemMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <p> SystemRoleSeniorServiceImpl </p>
 * <p> Description : SystemRoleSeniorServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-04-29 13:59 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "systemRoleSeniorService")
public class SystemRoleSeniorServiceImpl implements SystemRoleSeniorService {

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemMenuService systemMenuService;

    @Override
    public List<SystemMenuTreeModel> findTreeMenuById(SystemRoleModel roleModel, SystemMenuTypeModel typeModel) {
        Map<Long, SystemMenuTreeModel> treeMap = new ConcurrentHashMap<>();
        // All currently available menus
        Iterable<SystemMenuModel> activedMenus = systemMenuService.getByType(typeModel);
        // The current permission has a menu
        SystemRoleModel role = this.systemRoleService.getModelById(roleModel.getId());
        Map<Long, SystemMenuModel> roleMenus = new ConcurrentHashMap<>();
        // Populate own menu
        role.getMenuList().forEach(menu -> roleMenus.put(menu.getId(), menu));
        // Into the list
        List<SystemMenuModel> menuList = StreamSupport.stream(activedMenus.spliterator(), false)
                .sorted(Comparator.comparing(SystemMenuModel::getParent))
                .collect(Collectors.toList());
        // Sets the parent menu sort
        menuList.forEach(menu -> {
            SystemMenuTreeModel parent = new SystemMenuTreeModel();
            SysteMenuTreeItemModel item = SysteMenuTreeItemModel.buildNew();
            item.setPhrase(menu.getId());
            parent.setItem(item);
            if (menu.getParent() == 0) {
                // The main menu
                parent.setId(menu.getId());
                parent.setName(menu.getName());
                if (!ObjectUtils.isEmpty(roleMenus.get(menu.getId()))) {
                    parent.setChecked(Boolean.TRUE);
                    parent.setSelected(Boolean.TRUE);
                }
                treeMap.put(menu.getId(), parent);
            } else {
                // Sub menu
                SystemMenuTreeModel temp = treeMap.get(menu.getParent());
                List<SystemMenuTreeModel> childrens = temp.getChildren();
                // Automatically sets a collection of submenus if the current submenu does not belong to the main menu
                if (ObjectUtils.isEmpty(childrens)) {
                    childrens = new ArrayList<>();
                }
                SystemMenuTreeModel children = new SystemMenuTreeModel();
                children.setId(menu.getId());
                children.setName(menu.getName());
                SysteMenuTreeItemModel childrenItem = SysteMenuTreeItemModel.buildNew();
                childrenItem.setPhrase(menu.getId());
                children.setItem(item);
                if (!ObjectUtils.isEmpty(roleMenus.get(menu.getId()))) {
                    children.setChecked(Boolean.TRUE);
                    children.setSelected(Boolean.TRUE);
                }
                childrens.add(children);
                temp.setChildren(childrens);
                treeMap.put(temp.getId(), temp);
            }
        });
        // Convert Map data to List
        List<SystemMenuTreeModel> tree = new ArrayList<>();
        treeMap.keySet().forEach(v -> tree.add(treeMap.get(v)));
        return tree;
    }

    @Override
    public List<SystemMenuTreeModel> findMenuById(Long id) {
        return null;
    }

    @Override
    public List<SystemMenuTreeModel> findMenuByIds(List<SystemRoleModel> roles) {
        List<SystemMenuModel> list = new ArrayList<>();
        roles.forEach(role -> {
            List<SystemMenuModel> menus = role.getMenuList().stream().filter(v -> v.getType().getId() == 3).collect(Collectors.toList());
            list.addAll(menus);
        });
        return this.getTree(list.stream().distinct().collect(Collectors.toList()));
    }

    /**
     * convert to tree model
     *
     * @param roles source role list
     * @return tree model list
     */
    private List<SystemMenuTreeModel> getTree(List<SystemMenuModel> roles) {
        Map<Long, SystemMenuTreeModel> treeMap = new ConcurrentHashMap<>();
        // Assembly menu, divided into father and son menu
        roles.forEach((SystemMenuModel menu) -> {
            SystemMenuTreeModel parent = new SystemMenuTreeModel();
            if (menu.getParent() == 0) {
                // The main menu
                BeanUtils.copyProperties(menu, parent);
                treeMap.put(menu.getId(), parent);
            } else {
                SystemMenuTreeModel children = new SystemMenuTreeModel();
                BeanUtils.copyProperties(menu, children);
                // Sub menu
                SystemMenuTreeModel temp = treeMap.get(menu.getParent());
                // The parent menu of the current submenu is not buffered
                if (ObjectUtils.isEmpty(temp)) {
                    treeMap.put(menu.getParent(), children);
                    // Reextract data
                    temp = treeMap.get(menu.getParent());
                }
                // Set parent menu as new function when subset menu has new function
                if (menu.getNewd()) {
                    temp.setNewd(Boolean.TRUE);
                }
                List<SystemMenuTreeModel> childrens = temp.getChildren();
                // Automatically sets a collection of submenus if the current submenu does not belong to the main menu
                if (ObjectUtils.isEmpty(childrens)) {
                    childrens = new ArrayList<>();
                }
                childrens.add(children);
                temp.setChildren(childrens);
                treeMap.put(menu.getParent(), temp);
            }
        });
        // Convert Map data to List
        List<SystemMenuTreeModel> tree = new ArrayList<>();
        treeMap.keySet().forEach(v -> tree.add(treeMap.get(v)));
        // Reorder the menu by sort field
        tree.sort(Comparator.comparing(SystemMenuTreeModel::getSorted));
        return tree;
    }

}
