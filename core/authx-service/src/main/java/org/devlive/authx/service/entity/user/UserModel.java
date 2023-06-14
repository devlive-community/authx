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
package org.devlive.authx.service.entity.user;

import org.devlive.authx.service.entity.system.role.SystemRoleModel;
import org.devlive.authx.service.entity.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * <p> UserModel </p>
 * <p> Description : UserModel </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 20:36 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = {
        "password",
})
public class UserModel extends BaseModel {

    @Column(name = "password")
    private String password; // 密码

    @Column(name = "avatar")
    private String avatar; // 头像

    @Column(name = "email")
    private String email; // 邮箱

    @Column(name = "locked")
    private Boolean locked = false; // 是否锁定

    @Column(name = "systemed")
    private Boolean systemed = false; // 是否为系统默认,系统默认用户无法做任何操作

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_system_role_relation",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_role_id", referencedColumnName = "id"))
    private List<SystemRoleModel> roles;

}
