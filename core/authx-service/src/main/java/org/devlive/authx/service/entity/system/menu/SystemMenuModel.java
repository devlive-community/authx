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
package org.devlive.authx.service.entity.system.menu;

import org.devlive.authx.common.support.DateSuooprt;
import org.devlive.authx.service.entity.MethodEntity;
import org.devlive.authx.service.entity.icon.IconModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * <p> SystemMenuModel </p>
 * <p> Description : SystemMenuModel </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 15:25 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "system_menu")
public class SystemMenuModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "url")
    private String url;

    @Column(name = "sorted")
    private Integer sorted;

    @Column(name = "level")
    private Integer level;

    @Column(name = "tips")
    private String tips;

    @Column(name = "newd")
    private Boolean newd;

    @Column(name = "parent")
    private Long parent;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "create_time")
    @CreatedDate
    @DateTimeFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    private Date createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    @DateTimeFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    private Date updateTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "system_menu_type_relation",
            joinColumns = @JoinColumn(name = "system_menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_menu_type_id", referencedColumnName = "id"))
    private SystemMenuTypeModel type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_menu_method_relation",
            joinColumns = @JoinColumn(name = "system_menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_method_id", referencedColumnName = "id"))
    private List<MethodEntity> methods;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "system_menu_icon_relation",
            joinColumns = @JoinColumn(name = "system_menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "icon_id", referencedColumnName = "id"))
    private IconModel icon;

    public SystemMenuModel(Long id) {
        this.id = id;
    }

}
