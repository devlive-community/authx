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
package org.devlive.authx.storage.mongodb.service.system.log.impl;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.storage.mongodb.repository.system.SystemLogToMongoDbRepository;
import org.devlive.authx.storage.mongodb.service.system.log.SystemLogToMongoDbService;
import org.devlive.authx.storage.mongodb.model.system.SystemLogToMongoDbModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * <p> SystemLogServiceImpl </p>
 * <p> Description : SystemLogServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-10-21 15:31 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "systemLogToMongoDbService")
public class SystemLogToMongoDbServiceImpl implements SystemLogToMongoDbService {

    @Autowired
    private SystemLogToMongoDbRepository repository;

    @Override
    public SystemLogToMongoDbModel insertModel(SystemLogToMongoDbModel model) {
        return this.repository.save(model);
    }

    @Override
    public PageModel<SystemLogToMongoDbModel> getAllByPage(Pageable pageable) {
        Page<SystemLogToMongoDbModel> pageModel = this.repository.findAll(pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public SystemLogToMongoDbModel getModelById(String id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public long getCount() {
        return this.repository.count();
    }

}
