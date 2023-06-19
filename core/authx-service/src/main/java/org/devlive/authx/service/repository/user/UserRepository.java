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
package org.devlive.authx.service.repository.user;

import org.devlive.authx.service.entity.user.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <p> UserRepository </p>
 * <p> Description : UserRepository </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 20:45 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface UserRepository extends PagingAndSortingRepository<UserModel, Long> {

    /**
     * find model by username and password
     *
     * @param name     username
     * @param password password
     * @return user model
     */
    UserModel findByNameAndPassword(String name, String password);

    /**
     * find model by username
     *
     * @param name username
     * @return user model
     */
    UserModel findByName(String name);

    UserModel findDistinctById(Long id);

    /**
     * 查询所有的非系统用户信息
     *
     * @param pageable 分页查询信息
     * @return 所有的非系统用户信息
     */
    Page<UserModel> findAllByIsSystemIsFalse(Pageable pageable);

}
