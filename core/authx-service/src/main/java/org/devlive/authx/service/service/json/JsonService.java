package org.devlive.authx.service.service.json; /**
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

import org.devlive.authx.service.entity.common.CommonResponseModel;
import org.devlive.authx.service.service.BaseService;

/**
 * <p> JsonService </p>
 * <p> Description : JsonService </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 18:35 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface JsonService extends BaseService {

    /**
     * 格式化json数据
     *
     * @param source json元数据
     * @return 格式化后的json数据
     */
    CommonResponseModel formatPretty(String source);

    /**
     * 压缩json数据
     *
     * @param source json元数据
     * @return 压缩后的json数据
     */
    CommonResponseModel compression(String source);

}
