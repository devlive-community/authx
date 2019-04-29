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

import com.bootstack.model.system.menu.SystemMenuTypeModel;
import com.bootstack.model.system.role.SystemMenuTreeModel;
import com.bootstack.model.system.role.SystemRoleModel;

import java.util.List;

/**
 * <p> SystemRoleSeniorService </p>
 * <p> Description : SystemRoleSeniorService </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-04-29 13:54 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface SystemRoleSeniorService {

    /**
     * Query the menu by the permission ID
     * @param id
     * @param flag
     * @return
     */
    List<SystemMenuTreeModel> findTreeMenuById(SystemRoleModel roleModel, SystemMenuTypeModel typeModel);

    List<SystemMenuTreeModel> findMenuById(Long id);

    List<SystemMenuTreeModel> findMenuByIds(List<SystemRoleModel> roles);

}
