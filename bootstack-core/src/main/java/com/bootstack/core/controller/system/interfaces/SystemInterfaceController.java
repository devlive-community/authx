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
package com.bootstack.core.controller.system.interfaces;

import com.bootstack.common.pinyin.PinYinUtils;
import com.bootstack.core.support.ParamSupport;
import com.bootstack.model.common.CommonResponseModel;
import com.bootstack.model.system.interfaces.SystemInterfaceModel;
import com.bootstack.model.system.method.SystemMethodModel;
import com.bootstack.param.page.PageParam;
import com.bootstack.param.system.interfaces.SystemInterfaceCreateParam;
import com.bootstack.param.system.interfaces.SystemInterfaceSetParam;
import com.bootstack.service.system.interfaces.SystemInterfaceService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> SystemInterfaceController </p>
 * <p> Description : SystemInterfaceController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-03-25 15:20 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/system/interface")
@Slf4j
public class SystemInterfaceController {

    @Autowired
    private SystemInterfaceService systemInterfaceService;

    /**
     * find all model
     *
     * @param param page info param
     * @return all model for page
     */
    @GetMapping
    public CommonResponseModel list(@Validated PageParam param) {
        Pageable pageable = ParamSupport.getPageable(param);
        return CommonResponseModel.success(this.systemInterfaceService.getAll(pageable));
    }

    /**
     * add system interface
     *
     * @param param role info
     * @return add response
     */
    @PostMapping
    public CommonResponseModel add(@RequestBody @Validated SystemInterfaceCreateParam param) {
        SystemInterfaceModel model = new SystemInterfaceModel();
        BeanUtils.copyProperties(param, model);
        model.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        List<SystemMethodModel> methods = Lists.newArrayList();
        param.getMethod().forEach(v -> {
            SystemMethodModel method = new SystemMethodModel();
            method.setId(Long.valueOf(v));
            methods.add(method);
        });
        model.setMethods(methods);
        return CommonResponseModel.success(this.systemInterfaceService.insertModel(model));
    }

    @PutMapping
    public CommonResponseModel set(@RequestBody @Validated SystemInterfaceSetParam param) {
        SystemInterfaceModel model = new SystemInterfaceModel();
        model.setId(Long.valueOf(param.getId()));
        BeanUtils.copyProperties(param, model);
        model.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.systemInterfaceService.insertModel(model));
    }

}
