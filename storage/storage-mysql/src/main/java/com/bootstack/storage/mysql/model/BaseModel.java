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
package com.bootstack.storage.mysql.model;

import com.bootstack.common.support.DateSuooprt;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> BaseModel </p>
 * <p> Description : BaseModel </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-25 20:45 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 唯一标志

    @Column(name = "name")
    private String name; // 名称

    @Column(name = "active")
    private Boolean active = true; // 激活状态

    @Column(name = "create_time")
    @CreatedDate
    @DateTimeFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    @JsonFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    private Date createTime; // 创建时间

    @Column(name = "update_time")
    @LastModifiedDate
    @DateTimeFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    @JsonFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    private Date updateTime; // 更新时间

}
