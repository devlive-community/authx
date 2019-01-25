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
package com.bootstack.service.user;

import com.bootstack.model.user.UserModel;
import com.bootstack.repository.user.UserRepository;
import com.bootstack.service.ServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    private UserRepository userRepository;

    @Override
    public Long insertModel(UserModel model) {
        UserModel user = this.userRepository.save(model);
        if (!ObjectUtils.isEmpty(user)) {
            return user.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public UserModel getModelById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public UserModel getModelByNameAndPassword(String name, String password) {
        return this.userRepository.findByNameAndPassword(name, password);
    }

    @Override
    public UserModel getModelByName(String name) {
        return this.userRepository.findByName(name);
    }

}