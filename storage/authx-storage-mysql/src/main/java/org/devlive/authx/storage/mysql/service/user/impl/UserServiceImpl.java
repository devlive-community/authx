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
package org.devlive.authx.storage.mysql.service.user.impl;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.storage.mysql.model.user.UserModel;
import org.devlive.authx.storage.mysql.repository.user.UserRepository;
import org.devlive.authx.storage.mysql.service.ServiceSupport;
import org.devlive.authx.storage.mysql.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

/**
 * <p> UserServiceImpl </p>
 * <p> Description : UserServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 09:50 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Long insertModel(Object model) {
        UserModel source = (UserModel) model;
        UserModel user = this.repository.save(source);
        if (!ObjectUtils.isEmpty(user)) {
            return user.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public UserModel getModelById(Long id) {
        Optional<UserModel> model = this.repository.findById(id);
        if (model.isPresent()) {
            return model.get();
        }
        return null;
    }

    @Override
    public PageModel getAllByPage(Pageable pageable) {
        Page<UserModel> pageModel = this.repository.findAllBySystemedFalse(pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public UserModel getModelByNameAndPassword(String name, String password) {
        return this.repository.findByNameAndPassword(name, password);
    }

    @Override
    public UserModel getModelByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public UserModel getDistinctById(Long id) {
        return this.repository.findDistinctById(id);
    }

    @Override
    public long getCount() {
        return this.repository.count();
    }

}
