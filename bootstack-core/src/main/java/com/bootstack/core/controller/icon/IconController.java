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
package com.bootstack.core.controller.icon;

import com.bootstack.common.pinyin.PinYinUtils;
import com.bootstack.model.common.CommonResponseModel;
import com.bootstack.model.icon.IconModel;
import com.bootstack.model.icon.IconTypeModel;
import com.bootstack.model.page.PageModel;
import com.bootstack.param.icon.IconCreateParam;
import com.bootstack.param.icon.IconSetParam;
import com.bootstack.param.page.PageParam;
import com.bootstack.service.icon.IconService;
import com.bootstack.service.icon.IconTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p> IconController </p>
 * <p> Description : IconController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-08 18:06 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/icon")
@Slf4j
public class IconController {

    @Autowired
    private IconService service;

    @Autowired
    private IconTypeService iconTypeService;

    @GetMapping
    CommonResponseModel getAll(@Validated PageParam param) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.service.getAllByPage(pageable));
    }

    @PostMapping
    CommonResponseModel add(@RequestBody @Validated IconCreateParam param) {
        IconModel model = new IconModel();
        BeanUtils.copyProperties(param, model);
        model.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        IconTypeModel type = (IconTypeModel) this.iconTypeService.getModelById(Long.valueOf(param.getType()));
        model.setType(type);
        return CommonResponseModel.success(this.service.insertModel(model));
    }

    @PutMapping
    CommonResponseModel put(@RequestBody @Validated IconSetParam param) {
        IconModel model = new IconModel();
        BeanUtils.copyProperties(param, model);
        model.setId(Long.valueOf(param.getId()));
        model.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        IconTypeModel type = (IconTypeModel) this.iconTypeService.getModelById(Long.valueOf(param.getType()));
        model.setType(type);
        return CommonResponseModel.success(this.service.insertModel(model));
    }

}
