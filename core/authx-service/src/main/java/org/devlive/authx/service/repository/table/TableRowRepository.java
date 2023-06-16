package org.devlive.authx.service.repository.table; /**
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

import org.devlive.authx.service.entity.system.menu.SystemMenuModel;
import org.devlive.authx.service.entity.table.TableRowModel;
import org.devlive.authx.service.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p> TableRowRepository </p>
 * <p> Description : TableRowRepository </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-31 14:37 </p>
 * <p> Author Eamil: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public interface TableRowRepository extends BaseRepository<TableRowModel, Long> {

    /**
     * 根据菜单查询菜单的表头信息
     *
     * @param menus    菜单信息
     * @param pageable 分页信息
     * @return 表头信息
     */
    Page<TableRowModel> findAllByMenusIn(List<SystemMenuModel> menus, Pageable pageable);

}