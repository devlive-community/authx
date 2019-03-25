package com.bootstack.service.system.role; /**
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

import com.bootstack.model.page.PageModel;
import com.bootstack.model.system.role.SystemRoleModel;
import org.springframework.data.domain.Pageable;

/**
 * <p> SystemRoleService </p>
 * <p> Description : SystemRoleService </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 01:01 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface SystemRoleService {

    /**
     * insert model
     *
     * @param model model info
     * @return insert result
     */
    Long insertModel(SystemRoleModel model);

    /**
     * get model by id
     *
     * @param id id
     * @return model response id
     */
    SystemRoleModel getModelById(Long id);

    /**
     * get all model
     *
     * @param pageable page info
     * @return all model
     */
    PageModel<SystemRoleModel> getAll(Pageable pageable);

    /**
     * get model by name
     *
     * @param name name
     * @return model response by name
     */
    SystemRoleModel getModelByName(String name);

}
