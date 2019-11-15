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
package com.bootstack.storage.mysql.service.system.role;

import com.bootstack.common.page.PageModel;
import com.bootstack.storage.mysql.model.system.role.SystemRoleModel;
import com.bootstack.storage.mysql.repository.system.role.SystemRoleRepository;
import com.bootstack.storage.mysql.service.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * <p> SystemRoleServiceImpl </p>
 * <p> Description : SystemRoleServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 01:01 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "systemRoleService")
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleRepository repository;

    @Override
    public Long insertModel(SystemRoleModel model) {
        SystemRoleModel systemRole = this.repository.save(model);
        if (!ObjectUtils.isEmpty(systemRole)) {
            return systemRole.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public SystemRoleModel getModelById(Long id) {
        Optional<SystemRoleModel> model = this.repository.findById(id);
        if (model.isPresent()) {
            return model.get();
        }
        return null;
    }

    @Override
    public PageModel<SystemRoleModel> getAll(Pageable pageable) {
        Page<SystemRoleModel> models = this.repository.findAll(pageable);
        return new PageModel<>(models.getContent(), pageable, models.getTotalElements());
    }

    @Override
    public SystemRoleModel getModelByName(String name) {
        return this.repository.findByName(name);
    }

}
