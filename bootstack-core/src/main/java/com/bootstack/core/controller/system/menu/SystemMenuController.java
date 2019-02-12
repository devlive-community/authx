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
package com.bootstack.core.controller.system.menu;

import com.bootstack.core.controller.ControllerSupport;
import com.bootstack.model.common.CommonResponseModel;
import com.bootstack.model.system.menu.SystemMenuModel;
import com.bootstack.param.system.menu.SystemMenuBasicParam;
import com.bootstack.service.system.menu.SystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> SystemMenuController </p>
 * <p> Description : SystemMenuController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 15:56 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/system/menu")
@Slf4j
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * create new menu
     *
     * @param param menu info
     * @return create result
     */
    @PostMapping(value = ControllerSupport.CONTROLLER_DEFAULT_ADD)
    CommonResponseModel add(@RequestBody @Validated SystemMenuBasicParam param) {
        SystemMenuModel systemMenuModel = new SystemMenuModel();
        BeanUtils.copyProperties(param, systemMenuModel);
        systemMenuModel.setActive(Boolean.TRUE);
        return CommonResponseModel.success(this.systemMenuService.insertModel(systemMenuModel));
    }

}