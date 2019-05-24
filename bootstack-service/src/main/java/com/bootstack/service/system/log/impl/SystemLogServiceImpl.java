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
package com.bootstack.service.system.log.impl;

import com.bootstack.model.page.PageModel;
import com.bootstack.model.system.log.SystemLogModel;
import com.bootstack.repository.system.log.SystemLogRepository;
import com.bootstack.service.ServiceSupport;
import com.bootstack.service.system.log.SystemLogService;
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
@Service(value = "systemLogService")
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogRepository systemLogRepository;

    @Override
    public Long insertModel(Object model) {
        SystemLogModel source = (SystemLogModel) model;
        SystemLogModel user = this.systemLogRepository.save(source);
        if (!ObjectUtils.isEmpty(user)) {
            return user.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public Object getModelById(Long id) {
        return this.systemLogRepository.findOne(id);
    }

    @Override
    public PageModel getAllByPage(Pageable pageable) {
        Page<SystemLogModel> pageModel = this.systemLogRepository.findAll(pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public long getCount() {
        return this.systemLogRepository.count();
    }

}
