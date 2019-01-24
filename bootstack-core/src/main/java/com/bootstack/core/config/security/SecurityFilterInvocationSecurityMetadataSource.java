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
package com.bootstack.core.config.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p> SecurityFilterInvocationSecurityMetadataSource </p>
 * <p> Description : SecurityFilterInvocationSecurityMetadataSource </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 15:53 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Service(value = "securityFilterInvocationSecurityMetadataSource")
public class SecurityFilterInvocationSecurityMetadataSource implements
        FilterInvocationSecurityMetadataSource {

    // This method is used to determine whether the url requested by the user is in the permission table, and if it is in the permission table, it is returned to the decide method to determine whether the user has this permission. Release if not in the permission table.
    // I'm going to direct interception, direct interception, whatever the requested url is, and then in SecurityAccessDecisionManager decide method to intercept or release of decision making.
    // So the return value of this method can't return null and I'm just going to return it here.
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> config = new ArrayList<>();
        config.add(new SecurityConfig("null"));
        return config;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}