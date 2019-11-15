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
package com.bootstack.storage.mysql.service.system.log.impl;

import com.bootstack.common.page.PageModel;
import com.bootstack.storage.mysql.model.system.log.SystemLogTypeModel;
import com.bootstack.storage.mysql.repository.system.log.SystemLogTypeRepository;
import com.bootstack.storage.mysql.service.ServiceSupport;
import com.bootstack.storage.mysql.service.system.log.SystemLogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p> SystemLogTypeServiceImpl </p>
 * <p> Description : SystemLogTypeServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-07 14:31 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "systemLogTypeService")
public class SystemLogTypeServiceImpl implements SystemLogTypeService {

    @Autowired
    private SystemLogTypeRepository systemLogTypeRepository;

    @Override
    public Long insertModel(Object model) {
        SystemLogTypeModel source = (SystemLogTypeModel) model;
        SystemLogTypeModel user = this.systemLogTypeRepository.save(source);
        if (!ObjectUtils.isEmpty(user)) {
            return user.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public Object getModelById(Long id) {
        return this.systemLogTypeRepository.findById(id);
    }

    @Override
    public PageModel getAllByPage(Pageable pageable) {
        Page<SystemLogTypeModel> pageModel = this.systemLogTypeRepository.findAll(pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public long getCount() {
        return this.systemLogTypeRepository.count();
    }

}
