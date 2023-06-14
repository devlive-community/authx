package org.devlive.authx.service.repository.system.interfaces; /**
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

import org.devlive.authx.service.entity.system.interfaces.SystemInterfaceModel;
import org.devlive.authx.service.entity.system.method.SystemMethodModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * <p> SystemInterfaceRepository </p>
 * <p> Description : SystemInterfaceRepository </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 22:40 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface SystemInterfaceRepository extends PagingAndSortingRepository<SystemInterfaceModel, Long> {

    /**
     * find all white list
     *
     * @param pageable page info
     * @return all white list
     */
    Page<SystemInterfaceModel> findAllByWhiteTrueAndActiveTrue(Pageable pageable);

    /**
     * get all whilte list
     *
     * @return all white list
     */
    Iterable<SystemInterfaceModel> findAllByWhiteIsTrueAndActiveTrueAndSystemTrue();

    /**
     * find by path like ?
     *
     * @param path path
     * @return info
     */
    SystemInterfaceModel findByPathLike(String path);

    /**
     * get all model by path like ?
     *
     * @param path path
     * @return all model for path like ?
     */
    Iterable<SystemInterfaceModel> findAllByPathLike(String path);

    /**
     * find by path like ? and system default is false
     *
     * @param path path
     * @return info
     */
    SystemInterfaceModel findByPathLikeAndSystemFalse(String path);

    /**
     * find by path like ? and method in method list
     *
     * @param path    path
     * @param methods method list
     * @return info
     */
    SystemInterfaceModel findByPathLikeAndMethodsInAndSystemFalse(String path, List<SystemMethodModel> methods);

    /**
     * find by path and method in method list
     *
     * @param path    path
     * @param methods method list
     * @return info
     */
    SystemInterfaceModel findByPathAndSystemFalseAndMethodsIn(String path, List<SystemMethodModel> methods);

    /**
     * 根据请求路径和请求方式查询数据
     *
     * @param path    请求路径
     * @param methods 请求方式列表
     * @return 当前查询条件返回数据
     */
    SystemInterfaceModel findByPathAndSystemTrueAndWhiteTrueAndActiveTrueAndMethodsIn(String path, List<SystemMethodModel> methods);

}
