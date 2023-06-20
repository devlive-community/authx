/**
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
package org.devlive.authx.service.entity.table;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.authx.service.entity.BaseEntity;
import org.devlive.authx.service.entity.system.menu.SystemMenuModel;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * <p> TableRowModel </p>
 * <p> Description : TableRowModel </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-30 16:11 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "table_row")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TableRowEntity extends BaseEntity
{

    private String title; // 名称
    private Boolean checked = false; // 选中状态
    private String properties; // 对应数据的字段
    private String type; // 字段类型,后期支持排序等功能
    private Integer sorted; // 排列顺序

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "table_row_system_menu_relation",
            joinColumns = @JoinColumn(name = "system_menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "table_row_id", referencedColumnName = "id"))
    private List<SystemMenuModel> menus; // 可以使用的菜单列表,只能是菜单使用
}
