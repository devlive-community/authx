package org.devlive.authx.storage.mysql.service.system.method; /**
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

import org.devlive.authx.storage.mysql.model.system.method.SystemMethodModel;
import org.devlive.authx.storage.mysql.service.BaseService;

/**
 * <p> SystemMethodService </p>
 * <p> Description : SystemMethodService </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-04-24 14:21 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface SystemMethodService extends BaseService {

    /**
     * get info by method
     *
     * @param method method
     * @return info
     */
    SystemMethodModel getByMethod(String method);

}
