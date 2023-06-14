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
package org.devlive.authx.service.service.system.menu;

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.system.menu.SystemMenuModel;
import org.devlive.authx.service.entity.system.menu.SystemMenuTypeModel;
import org.devlive.authx.service.service.ServiceSupport;
import org.devlive.authx.service.repository.system.menu.SystemMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p> SystemMenuServiceImpl </p>
 * <p> Description : SystemMenuServiceImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 15:40 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "systemMenuService")
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuRepository systemMenuRepository;

    @Override
    public Long insertModel(Object model) {
        SystemMenuModel source = (SystemMenuModel) model;
        SystemMenuModel user = this.systemMenuRepository.save(source);
        if (!ObjectUtils.isEmpty(user)) {
            return user.getId();
        }
        return ServiceSupport.DEFAULT_ID;
    }

    @Override
    public Object getModelById(Long id) {
        return this.systemMenuRepository.findById(id);
    }

    @Override
    public PageModel getAllByPage(Pageable pageable) {
        Page<SystemMenuModel> pageModel = this.systemMenuRepository.findAll(pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public long getCount() {
        return this.systemMenuRepository.count();
    }

    @Override
    public PageModel<SystemMenuModel> getAllByParent(Long parent, Pageable pageable) {
        Page<SystemMenuModel> pageModel = this.systemMenuRepository.findAllByParent(parent, pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public PageModel<SystemMenuModel> getAllByParentAndType(Long parent, SystemMenuTypeModel type, Pageable pageable) {
        Page<SystemMenuModel> pageModel = this.systemMenuRepository.findAllByParentAndType(parent, type, pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public Iterable<SystemMenuModel> getByType(SystemMenuTypeModel type) {
        return this.systemMenuRepository.findByType(type);
    }

    @Override
    public PageModel<SystemMenuModel> getByPageAndType(Long type, Pageable pageable) {
        SystemMenuTypeModel typeModel = new SystemMenuTypeModel();
        typeModel.setId(type);
        Page<SystemMenuModel> pageModel = this.systemMenuRepository.findAllByType(typeModel, pageable);
        return new PageModel(pageModel.getContent(), pageable, pageModel.getTotalElements());
    }

    @Override
    public SystemMenuModel getModeByPath(String path) {
        return this.systemMenuRepository.findByUrl(path);
    }

}
