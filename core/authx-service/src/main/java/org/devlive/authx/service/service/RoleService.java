package org.devlive.authx.service.service; /**
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

import org.devlive.authx.common.page.PageModel;
import org.devlive.authx.service.entity.RoleEntity;
import org.springframework.data.domain.Pageable;

public interface RoleService
{

    /**
     * insert model
     *
     * @param model model info
     * @return insert result
     */
    Long insertModel(RoleEntity model);

    /**
     * get model by id
     *
     * @param id id
     * @return model response id
     */
    RoleEntity getModelById(Long id);

    /**
     * 分页获取所有数据，并按照 ID 排序
     */
    PageModel<RoleEntity> getAllByPage(Pageable pageable);

    /**
     * get model by name
     *
     * @param name name
     * @return model response by name
     */
    RoleEntity getModelByName(String name);

}
