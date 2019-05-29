package com.bootstack.repository; /**
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

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * <p> BaseRepository </p>
 * <p> Description : BaseRepository </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-25 21:00 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@NoRepositoryBean
public interface BaseRepository<BaseModel, ID extends Serializable> extends PagingAndSortingRepository<BaseModel, ID> {

    /**
     * 根据名称查询数据
     *
     * @param name 名称
     * @return 当前名称对应的数据
     */
    BaseModel findByName(String name);

    /**
     * 获取当前激活的数据
     *
     * @return 当前激活的数据
     */
    BaseModel findByActiveTrue();

}
