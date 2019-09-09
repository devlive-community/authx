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
package com.bootstack.storage.mysql.service.system.interfaces;

import com.bootstack.storage.mysql.model.page.PageModel;
import com.bootstack.storage.mysql.model.system.interfaces.SystemInterfaceModel;
import com.bootstack.storage.mysql.model.system.method.SystemMethodModel;
import com.bootstack.storage.mysql.repository.system.interfaces.SystemInterfaceRepository;
import com.bootstack.storage.mysql.service.ServiceSupport;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

/**
 * <p> SystemInterfaceServiceImpl </p>
 * <p> Description : SystemInterfaceServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 22:41 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "systemInterfaceService")
public class SystemInterfaceServiceImpl implements SystemInterfaceService {

    @Autowired
    private SystemInterfaceRepository systemInterfaceRepository;

    @Override
    public Long insertModel(SystemInterfaceModel model) {
        SystemInterfaceModel systemInterface = this.systemInterfaceRepository.save(model);
        if (!ObjectUtils.isEmpty(systemInterface)) {
            return systemInterface.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public PageModel<SystemInterfaceModel> getAllByWhiteTrueAndActiveTrue(Pageable pageable) {
        Page<SystemInterfaceModel> models = this.systemInterfaceRepository.findAllByWhiteTrueAndActiveTrue(pageable);
        return new PageModel<>(models.getContent(), pageable, models.getTotalElements());
    }

    @Override
    public Iterable<SystemInterfaceModel> getAllByWhiteIsTrueAndActiveTrueAndSystemTrue() {
        return this.systemInterfaceRepository.findAllByWhiteIsTrueAndActiveTrueAndSystemTrue();
    }

    @Override
    public SystemInterfaceModel getByPathLike(String path) {
        return this.systemInterfaceRepository.findByPathLike("%" + path + "%");
    }

    @Override
    public Iterable<SystemInterfaceModel> getAllByPathLike(String path) {
        return this.systemInterfaceRepository.findAllByPathLike("%" + path + "%");
    }

    @Override
    public SystemInterfaceModel getByPathLikeAndSystemFalse(String path) {
        return this.systemInterfaceRepository.findByPathLikeAndSystemFalse("%" + path + "%");
    }

    @Override
    public PageModel<SystemInterfaceModel> getAll(Pageable pageable) {
        Page<SystemInterfaceModel> models = this.systemInterfaceRepository.findAll(pageable);
        return new PageModel<>(models.getContent(), pageable, models.getTotalElements());
    }

    @Override
    public SystemInterfaceModel getByPathLikeAndMethodsIn(String path, List<SystemMethodModel> methods) {
        return this.systemInterfaceRepository.findByPathLikeAndMethodsInAndSystemFalse("%" + path + "%", methods);
    }

    @Override
    public SystemInterfaceModel getByPathLikeAndMethods(String path, SystemMethodModel method) {
        List<SystemMethodModel> methods = Lists.newArrayList();
        methods.add(method);
        return this.systemInterfaceRepository.findByPathLikeAndMethodsInAndSystemFalse("%" + path + "%", methods);
    }

    @Override
    public SystemInterfaceModel getByPathAndMethodsIn(String path, SystemMethodModel... methods) {
        return this.systemInterfaceRepository.findByPathAndSystemTrueAndWhiteTrueAndActiveTrueAndMethodsIn(path, Arrays.asList(methods));
    }

}
