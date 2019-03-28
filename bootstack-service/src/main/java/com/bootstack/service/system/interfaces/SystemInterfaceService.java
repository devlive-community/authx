package com.bootstack.service.system.interfaces; /**
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
import com.bootstack.model.system.interfaces.SystemInterfaceModel;
import org.springframework.data.domain.Pageable;

/**
 * <p> SystemInterfaceService </p>
 * <p> Description : SystemInterfaceService </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 22:41 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface SystemInterfaceService {

    /**
     * add system interface to system
     *
     * @param model model info
     * @return insert count
     */
    Long insertModel(SystemInterfaceModel model);

    /**
     * get all model by white is true and active is true
     *
     * @param pageable page info
     * @return all model
     */
    PageModel<SystemInterfaceModel> getAllByWhiteTrueAndActiveTrue(Pageable pageable);

    /**
     * get all model by white is true and active is true and system is true
     *
     * @return all model
     */
    Iterable<SystemInterfaceModel> getAllByWhiteIsTrueAndActiveTrueAndSystemTrue();

    /**
     * get model by path like ?
     *
     * @param path path
     * @return model info
     */
    SystemInterfaceModel getByPathLike(String path);

    /**
     * get all model by path like ?
     *
     * @param path path
     * @return all model for path like ?
     */
    Iterable<SystemInterfaceModel> getAllByPathLike(String path);

    /**
     * get model by path like ? and system is false
     *
     * @param path path
     * @return model info
     */
    SystemInterfaceModel getByPathLikeAndSystemFalse(String path);

    /**
     * get all model
     *
     * @param pageable page info
     * @return all model
     */
    PageModel<SystemInterfaceModel> getAll(Pageable pageable);

}
