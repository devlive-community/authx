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
package com.bootstack.core.controller.system.method;

import com.bootstack.common.pinyin.PinYinUtils;
import com.bootstack.storage.mysql.model.common.CommonResponseModel;
import com.bootstack.storage.mysql.model.page.PageModel;
import com.bootstack.storage.mysql.model.system.method.SystemMethodModel;
import com.bootstack.param.page.PageParam;
import com.bootstack.param.system.method.SystemMethodCreateParam;
import com.bootstack.storage.mysql.service.system.method.SystemMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p> SystemMethodController </p>
 * <p> Description : SystemMethodController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-04-22 15:20 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/system/method")
@Slf4j
public class SystemMethodController {

    @Autowired
    private SystemMethodService systemMethodService;

    /**
     * find all model
     *
     * @param param page info param
     * @return all model for page
     */
    @GetMapping
    public CommonResponseModel list(@Validated PageParam param) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.systemMethodService.getAllByPage(pageable));
    }

    /**
     * add model
     *
     * @param param mode info
     * @return add response
     */
    @PostMapping
    public CommonResponseModel add(@RequestBody @Validated SystemMethodCreateParam param) {
        SystemMethodModel model = new SystemMethodModel();
        BeanUtils.copyProperties(param, model);
        model.setActive(Boolean.TRUE);
        model.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.systemMethodService.insertModel(model));
    }

}
