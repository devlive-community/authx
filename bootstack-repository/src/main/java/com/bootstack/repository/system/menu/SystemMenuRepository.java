package com.bootstack.repository.system.menu; /**
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

import com.bootstack.model.system.menu.SystemMenuModel;
import com.bootstack.model.system.menu.SystemMenuTypeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <p> SystemMenuRepository </p>
 * <p> Description : SystemMenuRepository </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 15:39 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface SystemMenuRepository extends PagingAndSortingRepository<SystemMenuModel, Long> {

    Page<SystemMenuModel> findAllByActiveTrue(Pageable pageable);

    Page<SystemMenuModel> findAllByActiveTrueAndType(SystemMenuTypeModel model, Pageable pageable);

    /**
     * find all model by parent
     *
     * @param parent parent id
     * @return all model from parent
     */
    Page<SystemMenuModel> findAllByParent(Long parent, Pageable pageable);

    /**
     * 根据菜单父节点,菜单类型查询所有菜单数据并进行分页
     *
     * @param parent   菜单父节点
     * @param type     菜单类型
     * @param pageable 分页信息
     * @return 返回的数据
     */
    Page<SystemMenuModel> findAllByParentAndType(Long parent, SystemMenuTypeModel type, Pageable pageable);

    /**
     * find model by type
     *
     * @param type type info
     * @return model from type
     */
    Iterable<SystemMenuModel> findByType(SystemMenuTypeModel type);

    Page<SystemMenuModel> findAllByType(SystemMenuTypeModel type, Pageable pageable);

}
