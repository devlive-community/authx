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
package org.devlive.authx.server.controller.user;

import org.devlive.authx.common.encryption.EncryptionShaUtils;
import org.devlive.authx.common.enums.SystemMessageEnums;
import org.devlive.authx.server.controller.ControllerSupport;
import org.devlive.authx.storage.mysql.model.common.CommonResponseModel;
import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.storage.mysql.model.system.role.SystemRoleModel;
import org.devlive.authx.storage.mysql.model.user.UserModel;
import org.devlive.authx.param.page.PageParam;
import org.devlive.authx.param.user.UserBasicParam;
import org.devlive.authx.param.user.UserSetRoleParam;
import org.devlive.authx.storage.mysql.service.system.role.SystemRoleService;
import org.devlive.authx.storage.mysql.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
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

    @GetMapping
    public CommonResponseModel getAll(@Validated PageParam param) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.userService.getAllByPage(pageable));
    }

    /**
     * register user
     *
     * @param param user info
     * @return register response
     */
    @PostMapping(value = ControllerSupport.CONTROLLER_DEFAULT_ADD)
    public CommonResponseModel add(@RequestBody @Validated UserBasicParam param) {
        log.info("add user action, user name is {}", param.getName());
        if (!ObjectUtils.isEmpty(this.userService.getModelByName(param.getName()))) {
            return CommonResponseModel.error(SystemMessageEnums.SYSTEM_USER_EXISTS);
        }
        UserModel user = new UserModel();
        user.setName(param.getName());
        user.setPassword(EncryptionShaUtils.hash256(param.getPassword()));
        // default active this user
        user.setActive(Boolean.TRUE);
        return CommonResponseModel.success(this.userService.insertModel(user));
    }

    @GetMapping(value = "info/{name}")
    public CommonResponseModel info(@PathVariable String name) {
        return CommonResponseModel.success(this.userService.getModelByName(name));
    }

    /**
     * 分配用户权限
     *
     * @param param 分配权限参数(用户id, 权限id列表)
     * @return 分配状态
     */
    @PutMapping(value = "role")
    public CommonResponseModel setRole(@RequestBody @Validated UserSetRoleParam param) {
        UserModel user = (UserModel) this.userService.getModelById(Long.valueOf(param.getId()));
        // 抽取用户原有权限
        List<SystemRoleModel> systemRoles = user.getRoles();
        // 去除由于JPA导致的重复数据
        systemRoles = systemRoles.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparingLong(SystemRoleModel::getId))),
                        ArrayList::new));
        // 使用原有权限和现有权限做去重排查,防止用户调用api接口进行权限损坏
        List<Object> newRoles = param.getValues();
        systemRoles.forEach(v -> {
            for (int i = 0; i < newRoles.size(); i++) {
                if (String.valueOf(v.getId()).equalsIgnoreCase(String.valueOf(newRoles.get(i)))) {
                    // 如果原有权限中拥有新的权限信息,则删除新权限,保证数据只有一次落地
                    newRoles.remove(i);
                }
            }
        });
        List<SystemRoleModel> newRole = new ArrayList<>();
        newRole.addAll(systemRoles);
        // 创建新权限信息落地到数据库中
        newRoles.forEach(v -> {
            SystemRoleModel temp = new SystemRoleModel();
            temp.setId(Long.valueOf(String.valueOf(v)));
            newRole.add(temp);
        });
        user.setRoles(newRole);
        return CommonResponseModel.success(this.userService.insertModel(user));
    }

}
