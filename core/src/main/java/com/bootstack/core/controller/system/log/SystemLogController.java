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
package com.bootstack.core.controller.system.log;

import com.bootstack.common.pinyin.PinYinUtils;
import com.bootstack.storage.mysql.model.common.CommonResponseModel;
import com.bootstack.storage.mysql.model.page.PageModel;
import com.bootstack.storage.mysql.model.system.log.SystemLogTypeModel;
import com.bootstack.param.page.PageParam;
import com.bootstack.param.system.log.SystemLogTypeCreateParam;
import com.bootstack.param.system.log.SystemLogTypeSetParam;
import com.bootstack.storage.mysql.service.system.log.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p> SystemLogTypeController </p>
 * <p> Description : SystemLogTypeController </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-07 14:34 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@RestController
@RequestMapping(value = "${bootstack.api.path}/${bootstack.api.version}/system/log")
@Slf4j
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    @GetMapping
    public CommonResponseModel getAll(@Validated PageParam param) {
        Pageable pageable = PageModel.getPageable(param.getPage(), param.getSize());
        return CommonResponseModel.success(this.systemLogService.getAllByPage(pageable));
    }

    @PostMapping
    public CommonResponseModel add(@RequestBody @Validated SystemLogTypeCreateParam param) {
        SystemLogTypeModel logType = new SystemLogTypeModel();
        BeanUtils.copyProperties(param, logType);
        logType.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.systemLogService.insertModel(logType));
    }

    @PutMapping
    public CommonResponseModel put(@RequestBody @Validated SystemLogTypeSetParam param) {
        SystemLogTypeModel logType = new SystemLogTypeModel();
        BeanUtils.copyProperties(param, logType);
        logType.setId(Long.valueOf(param.getId()));
        logType.setCode(PinYinUtils.getFullFirstToUpper(param.getName()));
        return CommonResponseModel.success(this.systemLogService.insertModel(logType));
    }

    /**
     * 查询日志详情
     *
     * @param primaryKey 数据主键
     * @return 日志详情
     */
    @GetMapping(value = "/details")
    public CommonResponseModel infoDetail(@RequestParam(value = "primaryKey") Long primaryKey) {
        return CommonResponseModel.success(this.systemLogService.getModelById(primaryKey));
    }

}
