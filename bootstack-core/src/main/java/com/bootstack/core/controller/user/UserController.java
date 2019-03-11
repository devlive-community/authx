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
package com.bootstack.core.controller.user;

import com.bootstack.common.encryption.EncryptionShaUtils;
import com.bootstack.core.controller.ControllerSupport;
import com.bootstack.model.common.CommonResponseModel;
import com.bootstack.model.system.role.SystemRoleModel;
import com.bootstack.model.user.UserModel;
import com.bootstack.param.user.UserBasicParam;
import com.bootstack.param.user.UserSetRoleParam;
import com.bootstack.service.system.role.SystemRoleService;
import com.bootstack.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * <p> UserController </p>
 * <p> Description : UserController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 10:11 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SystemRoleService systemRoleService;

//    @PostMapping(value = ControllerSupport.CONTROLLER_DEFAULT_PUBLIC + ControllerSupport.CONTROLLER_DEFAULT_ADD)
    @PostMapping(value = ControllerSupport.CONTROLLER_DEFAULT_ADD)
    CommonResponseModel add(@RequestBody @Validated UserBasicParam param) {
        log.info("add user action, user name is {}", param.getName());
        UserModel user = new UserModel();
        user.setName(param.getName());
        user.setPassword(EncryptionShaUtils.hash256(param.getPassword()));
        // default active this user
        user.setActive(Boolean.TRUE);
        return CommonResponseModel.success(this.userService.insertModel(user));
    }

    @GetMapping(value = "info/{name}")
    CommonResponseModel info(@PathVariable String name) {
        return CommonResponseModel.success(this.userService.getModelByName(name));
    }

    @PutMapping(value = "role")
    CommonResponseModel setRole(@RequestBody @Validated UserSetRoleParam param) {
        UserModel user = (UserModel) this.userService.getModelById(Long.valueOf(param.getUserId()));
        List<SystemRoleModel> systemRoles = user.getRoles();
        // add new role to source role list
        systemRoles.add(this.systemRoleService.getModelById(Long.valueOf(param.getRoleId())));
        // distinct role
        systemRoles = systemRoles.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparingLong(SystemRoleModel::getId))),
                        ArrayList::new));
        user.setRoles(systemRoles);
        return CommonResponseModel.success(this.userService.insertModel(user));
    }

}