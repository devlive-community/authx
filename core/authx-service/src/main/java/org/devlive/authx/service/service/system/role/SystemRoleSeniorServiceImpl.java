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
package org.devlive.authx.service.service.system.role;

import org.devlive.authx.service.entity.system.menu.SystemMenuModel;
import org.devlive.authx.service.entity.system.menu.SystemMenuTypeModel;
import org.devlive.authx.service.entity.RoleEntity;
import org.devlive.authx.service.entity.tree.TreeItemModel;
import org.devlive.authx.service.entity.tree.TreeModel;
import org.devlive.authx.service.service.RoleService;
import org.devlive.authx.service.service.system.menu.SystemMenuService;
import org.devlive.authx.service.entity.icon.IconModel;
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
    private RoleService systemRoleService;

    @Autowired
    private SystemMenuService systemMenuService;

    @Override
    public List<TreeModel> findTreeMenuById(RoleEntity roleModel, SystemMenuTypeModel typeModel) {
        Map<Long, TreeModel> treeMap = new ConcurrentHashMap<>();
        // All currently available menus
        Iterable<SystemMenuModel> activedMenus = systemMenuService.getByType(typeModel);
        // The current permission has a menu
        RoleEntity role = this.systemRoleService.getModelById(roleModel.getId());
        Map<Long, SystemMenuModel> roleMenus = new ConcurrentHashMap<>();
        // Populate own menu
        role.getMenus().forEach(menu -> roleMenus.put(menu.getId(), menu));
        // Into the list
        List<SystemMenuModel> menuList = StreamSupport.stream(activedMenus.spliterator(), false)
                .sorted(Comparator.comparing(SystemMenuModel::getParent))
                .collect(Collectors.toList());
        // Sets the parent menu sort
        menuList.forEach(menu -> {
            TreeModel parent = new TreeModel();
            TreeItemModel item = TreeItemModel.buildNew();
            item.setPhrase(menu.getId());
            parent.setItem(item);
            if (menu.getParent() == 0) {
                // The main menu
                if (!ObjectUtils.isEmpty(menu.getIcon())) {
                    parent.setIcon(menu.getIcon().getCode());
                }
                parent.setId(menu.getId());
                parent.setName(menu.getName());
                parent.setTips(menu.getTips());
                if (!ObjectUtils.isEmpty(roleMenus.get(menu.getId()))) {
                    parent.setChecked(Boolean.TRUE);
                    parent.setSelected(Boolean.TRUE);
                }
                treeMap.put(menu.getId(), parent);
            } else {
                // Sub menu
                TreeModel temp = treeMap.get(menu.getParent());
                List<TreeModel> childrens = temp.getChildren();
                // Automatically sets a collection of submenus if the current submenu does not belong to the main menu
                if (ObjectUtils.isEmpty(childrens)) {
                    childrens = new ArrayList<>();
                }
                TreeModel children = new TreeModel();
                children.setId(menu.getId());
                children.setName(menu.getName());
                children.setTips(menu.getTips());
                if (!ObjectUtils.isEmpty(menu.getIcon())) {
                    children.setIcon(menu.getIcon().getCode());
                }
                TreeItemModel childrenItem = TreeItemModel.buildNew();
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
        List<TreeModel> tree = new ArrayList<>();
        treeMap.keySet().forEach(v -> tree.add(treeMap.get(v)));
        return tree;
    }

    @Override
    public List<TreeModel> findMenuById(Long id) {
        return null;
    }

    @Override
    public List<TreeModel> findMenuByIds(List<RoleEntity> roles) {
        List<SystemMenuModel> list = new ArrayList<>();
        roles.forEach(role -> {
            List<SystemMenuModel> menus = role.getMenus().stream().filter(v -> v.getType().getId() == 3).collect(Collectors.toList());
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
    private List<TreeModel> getTree(List<SystemMenuModel> roles) {
        Map<Long, TreeModel> treeMap = new ConcurrentHashMap<>();
        // Assembly menu, divided into father and son menu
//        roles.forEach((SystemMenuModel menu) -> {
//            TreeModel parent = new TreeModel();
//            if (menu.getParent() == 0) {
//                // The main menu
//                BeanUtils.copyProperties(menu, parent);
//                if (!ObjectUtils.isEmpty(menu.getIcon())) {
//                    parent.setIcon(menu.getIcon().getCode());
//                }
//                treeMap.put(menu.getId(), parent);
//            } else {
//                TreeModel children = new TreeModel();
//                BeanUtils.copyProperties(menu, children);
//                if (!ObjectUtils.isEmpty(menu.getIcon())) {
//                    children.setIcon(menu.getIcon().getCode());
//                }
//                // Sub menu
//                TreeModel temp = treeMap.get(menu.getParent());
//                // The parent menu of the current submenu is not buffered
//                if (ObjectUtils.isEmpty(temp)) {
//                    treeMap.put(menu.getParent(), children);
//                    // Reextract data
//                    temp = treeMap.get(menu.getParent());
//                }
//                // Set parent menu as new function when subset menu has new function
//                if (menu.getNewd()) {
//                    temp.setNewd(Boolean.TRUE);
//                }
//                List<TreeModel> childrens = temp.getChildren();
//                // Automatically sets a collection of submenus if the current submenu does not belong to the main menu
//                if (ObjectUtils.isEmpty(childrens)) {
//                    childrens = new ArrayList<>();
//                }
//                childrens.add(children);
//                temp.setChildren(childrens);
//                treeMap.put(menu.getParent(), temp);
//            }
//        });
        // Convert Map data to List
        List<TreeModel> tree = this.getChildren(0L, roles);
//        treeMap.keySet().forEach(v -> tree.add(treeMap.get(v)));
        // Reorder the menu by sort field
        tree.sort(Comparator.comparing(TreeModel::getSorted));
        return tree;
    }

    /**
     * 获取子节点数据
     *
     * @param id     当前父节点标志
     * @param models 数据集合
     * @return 树形结构数据
     */
    public List<TreeModel> getChildren(Long id, List<SystemMenuModel> models) {
        // 子数据存储器
        List<TreeModel> childrens = new ArrayList<>();
        for (SystemMenuModel model : models) {
            // 遍历所有节点,将所有数据的父id与传过来的根节点的id比较,或者-1.相等说明: 为该根节点的子节点
            if (model.getParent().equals(id)) {
                TreeModel support = new TreeModel();
                BeanUtils.copyProperties(model, support);
                IconModel icon = model.getIcon();
                if (!ObjectUtils.isEmpty(icon)) {
                    support.setIcon(model.getIcon().getCode());
                } else {
                    support.setIcon("");
                }
//                TreeModelItemSupport item = TreeModelItemSupport.buildNew();
//                item.setPhrase(entity.getId());
//                support.setItem(item);
//                support.setItem(model.getId());
                childrens.add(support);
            }
        }
        // 递归遍历数据填充树形结构
        for (TreeModel support : childrens) {
            support.setChildren(getChildren(support.getId(), models));
        }
        // 如果节点下没有子节点,返回一个空List(递归退出),暂时不做任何操作,直接递归退出
        if (childrens.size() == 0) {
            return null;
//            return new ArrayList<>();
        }
        return childrens;
    }

}
