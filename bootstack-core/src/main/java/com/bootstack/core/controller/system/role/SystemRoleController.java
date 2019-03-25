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
package com.bootstack.core.controller.system.role;

import com.bootstack.common.pinyin.PinYinUtils;
import com.bootstack.core.controller.ControllerSupport;
import com.bootstack.core.support.ParamSupport;
import com.bootstack.model.common.CommonResponseModel;
import com.bootstack.model.system.menu.SystemMenuModel;
import com.bootstack.model.system.role.SystemRoleModel;
import com.bootstack.param.page.PageParam;
import com.bootstack.param.system.role.SystemRoleBasicParam;
import com.bootstack.param.system.role.SystemRoleSetMenuParam;
import com.bootstack.param.system.role.SystemRoleSetParam;
import com.bootstack.service.system.role.SystemRoleService;
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

    /**
     * find all model
     *
     * @param param page info param
     * @return all model for page
     */
    @GetMapping
    CommonResponseModel list(@Validated PageParam param) {
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
    CommonResponseModel add(@RequestBody @Validated SystemRoleBasicParam param) {
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
    CommonResponseModel set(@RequestBody @Validated SystemRoleSetParam param) {
        SystemRoleModel systemRole = new SystemRoleModel();
        BeanUtils.copyProperties(param, systemRole);
        systemRole.setId(Long.valueOf(param.getId()));
        systemRole.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.systemRoleService.insertModel(systemRole));
    }

    @PutMapping(value = ControllerSupport.CONTROLLER_DEFAULT_SET + "/menu")
    CommonResponseModel setMenus(@RequestBody @Validated SystemRoleSetMenuParam param) {
        SystemRoleModel systemRole = this.systemRoleService.getModelById(Long.valueOf(param.getId()));
        List<SystemMenuModel> menuList = new ArrayList<>();
        Arrays.asList(param.getMenu().split(",")).forEach(v -> menuList.add(new SystemMenuModel(Long.valueOf(v))));
        systemRole.setMenuList(menuList);
        return CommonResponseModel.success(this.systemRoleService.insertModel(systemRole));
    }

}
