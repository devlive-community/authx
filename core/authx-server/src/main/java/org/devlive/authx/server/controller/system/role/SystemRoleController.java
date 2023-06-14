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
package org.devlive.authx.server.controller.system.role;

import org.devlive.authx.common.pinyin.PinYinUtils;
import org.devlive.authx.server.controller.ControllerSupport;
import org.devlive.authx.server.support.ParamSupport;
import org.devlive.authx.storage.mysql.model.common.CommonResponseModel;
import org.devlive.authx.storage.mysql.model.system.menu.SystemMenuModel;
import org.devlive.authx.storage.mysql.model.system.menu.SystemMenuTypeModel;
import org.devlive.authx.storage.mysql.model.system.role.SystemRoleModel;
import org.devlive.authx.storage.mysql.model.user.UserModel;
import org.devlive.authx.param.common.CommonMenuAndRoleParam;
import org.devlive.authx.param.page.PageParam;
import org.devlive.authx.param.system.role.SystemRoleBasicParam;
import org.devlive.authx.param.system.role.SystemRoleMenuParam;
import org.devlive.authx.param.system.role.SystemRoleSetMenuParam;
import org.devlive.authx.param.system.role.SystemRoleSetParam;
import org.devlive.authx.storage.mysql.service.system.menu.SystemMenuService;
import org.devlive.authx.storage.mysql.service.system.role.SystemRoleSeniorService;
import org.devlive.authx.storage.mysql.service.system.role.SystemRoleService;
import org.devlive.authx.storage.mysql.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p> SystemRoleController </p>
 * <p> Description : SystemRoleController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 16:16 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/system/role")
@Slf4j
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemRoleSeniorService systemRoleSeniorService;

    @Autowired
    private UserService userService;

    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * find all model
     *
     * @param param page info param
     * @return all model for page
     */
    @GetMapping
    public CommonResponseModel list(@Validated PageParam param) {
        Pageable pageable = ParamSupport.getPageable(param);
        return CommonResponseModel.success(this.systemRoleService.getAll(pageable));
    }

    /**
     * add a role to system
     *
     * @param param role info
     * @return add response
     */
    @PostMapping
    public CommonResponseModel add(@RequestBody @Validated SystemRoleBasicParam param) {
        SystemRoleModel systemRole = new SystemRoleModel();
        if (ObjectUtils.isEmpty(param.getActive())) {
            systemRole.setActive(Boolean.TRUE);
        }
        systemRole.setDescription(param.getDescription());
        systemRole.setName(param.getName());
        systemRole.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.systemRoleService.insertModel(systemRole));
    }

    @PutMapping
    public CommonResponseModel set(@RequestBody @Validated SystemRoleSetParam param) {
        SystemRoleModel systemRole = new SystemRoleModel();
        BeanUtils.copyProperties(param, systemRole);
        systemRole.setId(Long.valueOf(param.getId()));
        systemRole.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        SystemRoleModel temp = this.systemRoleService.getModelById(Long.valueOf(param.getId()));
        // 将权限信息还原
        systemRole.setMenuList(temp.getMenuList());
        return CommonResponseModel.success(this.systemRoleService.insertModel(systemRole));
    }

    @PutMapping(value = ControllerSupport.CONTROLLER_DEFAULT_SET + "/menu")
    public CommonResponseModel setMenus(@RequestBody @Validated SystemRoleSetMenuParam param) {
        SystemRoleModel systemRole = this.systemRoleService.getModelById(Long.valueOf(param.getId()));
        List<SystemMenuModel> menuList = new ArrayList<>();
        Arrays.asList(param.getMenu().split(",")).forEach(v -> menuList.add(new SystemMenuModel(Long.valueOf(v))));
        systemRole.setMenuList(menuList);
        return CommonResponseModel.success(this.systemRoleService.insertModel(systemRole));
    }

    /**
     * Assign system permissions menu
     *
     * @param param System permissions and menu information
     * @return Distribution state
     */
    @PutMapping(value = "menu")
    public CommonResponseModel setMenu(@RequestBody @Validated SystemRoleMenuParam param) {
        List<SystemMenuModel> menus = new ArrayList<>();
        List<String> menuId = param.getValue();
        SystemRoleModel model = this.systemRoleService.getModelById(Long.valueOf(param.getKey()));
        if (!ObjectUtils.isEmpty(menuId)) {
            menuId.stream().distinct().collect(Collectors.toList()).forEach(v -> {
                Long id = Long.valueOf(v);
                SystemMenuModel menu = new SystemMenuModel();
                menu.setId(id);
                menus.add(menu);
                // 如果未传递父菜单的话,自动将父菜单添加进来,防止转换菜单出现递归错误
                SystemMenuModel systemMenuModel = (SystemMenuModel) this.systemMenuService.getModelById(id);
                if (systemMenuModel.getParent() != 0) {
                    SystemMenuModel parent = new SystemMenuModel();
                    parent.setId(systemMenuModel.getParent());
                    menus.add(parent);
                }
            });
        }
        BeanUtils.copyProperties(param, model);
        // 抽取原有菜单并移除当前更新的权限类型数据,然后添加到授权菜单中
        List<SystemMenuModel> temp = model.getMenuList();
        temp = temp.stream().distinct().collect(Collectors.toList()).stream().filter(v -> v.getType().getId() != Long.valueOf(param.getMenuType())).collect(Collectors.toList());
        menus.addAll(temp);
        model.setMenuList(menus);
        return CommonResponseModel.success(this.systemRoleService.insertModel(model));
    }

    /**
     * Assign system permissions menu list
     *
     * @return 菜单列表
     */
    @GetMapping(value = "menu/tree")
    public CommonResponseModel findAllByRoleAndMenuType(@Validated CommonMenuAndRoleParam param) {
        SystemMenuTypeModel menuTypeModel = new SystemMenuTypeModel();
        menuTypeModel.setId(Long.valueOf(param.getMenuType()));
        SystemRoleModel roleModel = new SystemRoleModel();
        roleModel.setId(Long.valueOf(param.getRole()));
        return CommonResponseModel.success(this.systemRoleSeniorService.findTreeMenuById(roleModel, menuTypeModel));
    }

    /**
     * 获取系统左侧导航菜单
     *
     * @param id 权限标识
     * @return 导航菜单
     */
    @GetMapping(value = "menu")
    public CommonResponseModel getMenu(@RequestParam Long id) {
        UserModel user = (UserModel) this.userService.getDistinctById(id);
        // TODO: 判断权限的等级
        return CommonResponseModel.success(this.systemRoleSeniorService.findMenuByIds(user.getRoles()));
    }

}
