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
package org.devlive.authx.core.controller.icon;

import org.devlive.authx.common.pinyin.PinYinUtils;
import org.devlive.authx.storage.mysql.model.common.CommonResponseModel;
import org.devlive.authx.storage.mysql.model.icon.IconTypeModel;
import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.param.icon.IconTypeCreateParam;
import org.devlive.authx.param.icon.IconTypeSetParam;
import org.devlive.authx.param.page.PageParam;
import org.devlive.authx.storage.mysql.service.icon.IconTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p> IconTypeController </p>
 * <p> Description : IconTypeController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-08 18:06 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/icon/type")
@Slf4j
public class IconTypeController {

    @Autowired
    private IconTypeService iconTypeService;

    @GetMapping
    public CommonResponseModel getAll(@Validated PageParam param) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.iconTypeService.getAllByPage(pageable));
    }

    @PostMapping
    public CommonResponseModel add(@RequestBody @Validated IconTypeCreateParam param) {
        IconTypeModel iconType = new IconTypeModel();
        BeanUtils.copyProperties(param, iconType);
        iconType.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.iconTypeService.insertModel(iconType));
    }

    @PutMapping
    public CommonResponseModel put(@RequestBody @Validated IconTypeSetParam param) {
        IconTypeModel iconType = new IconTypeModel();
        BeanUtils.copyProperties(param, iconType);
        iconType.setId(Long.valueOf(param.getId()));
        iconType.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.iconTypeService.insertModel(iconType));
    }

}
