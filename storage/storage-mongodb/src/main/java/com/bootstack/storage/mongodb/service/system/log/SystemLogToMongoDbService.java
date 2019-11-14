package com.bootstack.storage.mongodb.service.system.log; /**
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

import com.bootstack.common.page.PageModel;
import com.bootstack.storage.mongodb.model.system.SystemLogToMongoDbModel;
import org.springframework.data.domain.Pageable;

/**
 * <p> SystemLogService </p>
 * <p> Description : SystemLogService </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-10-21 15:31 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface SystemLogToMongoDbService {

    SystemLogToMongoDbModel insertModel(SystemLogToMongoDbModel model);

    PageModel<SystemLogToMongoDbModel> getAllByPage(Pageable pageable);

    SystemLogToMongoDbModel getModelById(String id);

    long getCount();

}
