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
package org.devlive.authx.service.service.system.method;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.system.method.SystemMethodModel;
import org.devlive.authx.service.repository.system.method.SystemMethodRepository;
import org.devlive.authx.service.service.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p> SystemMethodServiceImpl </p>
 * <p> Description : SystemMethodServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-04-24 14:21 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "systemMethodService")
public class SystemMethodIServiceImpl implements SystemMethodIService
{

    @Autowired
    private SystemMethodRepository repository;

    @Override
    public Long insertModel(Object model) {
        SystemMethodModel source = (SystemMethodModel) model;
        SystemMethodModel temp = this.repository.save(source);
        if (!ObjectUtils.isEmpty(temp)) {
            return temp.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public Object getModelById(Long id) {
        return null;
    }

    @Override
    public PageModel getAllByPage(Pageable pageable) {
        Page<SystemMethodModel> models = this.repository.findAll(pageable);
        return new PageModel<>(models.getContent(), pageable, models.getTotalElements());
    }

    @Override
    public long getCount() {
        return this.repository.count();
    }

    @Override
    public SystemMethodModel getByMethod(String method) {
        return this.repository.findByMethod(method);
    }

}
