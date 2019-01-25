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
package com.bootstack.web.entity;

import com.bootstack.web.BootStackWebSupport;
import lombok.Data;
import lombok.ToString;
import org.springframework.core.env.Environment;

/**
 * <p> RemoteServerEntity </p>
 * <p> Description : RemoteServerEntity </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 17:02 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Data
@ToString
public class RemoteServerEntity {

    private String address;
    private String apiVersion;
    private String apiPath;
    private String apiGrantType;
    private String apiClientId;
    private Environment environment;

    public RemoteServerEntity(Environment environment) {
        this.address = environment.getProperty(BootStackWebSupport.CONFIG_SERVER_PREFIX + ".url");
        this.apiPath = environment.getProperty(BootStackWebSupport.CONFIG_SERVER_PREFIX + ".api.root");
        this.apiVersion = environment.getProperty(BootStackWebSupport.CONFIG_SERVER_PREFIX + ".api.version");
        this.apiGrantType = environment.getProperty(BootStackWebSupport.CONFIG_SERVER_PREFIX + ".api.grant-type");
        this.apiClientId = environment.getProperty(BootStackWebSupport.CONFIG_SERVER_PREFIX + ".api.client-id");
        this.environment = environment;
    }

    public String fullPath() {
        return address + apiPath + apiVersion;
    }

    public String oauthPath() {
        return address + environment.getProperty(BootStackWebSupport.CONFIG_SERVER_PREFIX + ".oauth");
    }

}