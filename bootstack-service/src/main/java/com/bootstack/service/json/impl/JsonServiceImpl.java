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
package com.bootstack.service.json.impl;

import com.bootstack.common.json.JsonUtils;
import com.bootstack.model.common.CommonResponseModel;
import com.bootstack.model.page.PageModel;
import com.bootstack.service.json.JsonService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p> JsonServiceImpl </p>
 * <p> Description : JsonServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 18:36 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "jsonService")
public class JsonServiceImpl implements JsonService {

    @Override
    public Long insertModel(Object model) {
        return null;
    }

    @Override
    public Object getModelById(Long id) {
        return null;
    }

    @Override
    public PageModel getAllByPage(Pageable pageable) {
        return null;
    }

    @Override
    public CommonResponseModel formatPretty(String source) {
        CommonResponseModel response = CommonResponseModel.validateCheck(source);
        if (!ObjectUtils.isEmpty(response)) {
            return response;
        }
        return CommonResponseModel.success(JsonUtils.formatPretty(source));
    }

    @Override
    public CommonResponseModel compression(String source) {
        CommonResponseModel response = CommonResponseModel.validateCheck(source);
        if (!ObjectUtils.isEmpty(response)) {
            return response;
        }
        return CommonResponseModel.success(JsonUtils.compression(source));
    }

}
