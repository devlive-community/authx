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
package com.bootstack.core.config.authorization;

import com.bootstack.core.config.handler.BootStackAccessDeniedHandler;
import com.bootstack.core.config.point.BootStackAuthenticationEntryPoint;
import com.bootstack.storage.mysql.model.system.method.SystemMethodModel;
import com.bootstack.storage.mysql.service.system.interfaces.SystemInterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.List;

/**
 * <p> BootStackAuthorizationResourceServerConfig </p>
 * <p> Description : BootStackAuthorizationResourceServerConfig </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 14:18 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Configuration
@EnableResourceServer
@Slf4j
public class BootStackAuthorizationResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    private BootStackAccessDeniedHandler bootStackAccessDeniedHandler;

    @Autowired
    private SystemInterfaceService systemInterfaceService;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(BootStackAuthorizationOauth2Support.SECURITY_RESOURCE_ID).tokenServices(resourceServerTokenServices)
                .accessDeniedHandler(bootStackAccessDeniedHandler)
                .authenticationEntryPoint(new BootStackAuthenticationEntryPoint());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        HttpSecurity.RequestMatcherConfigurer configurer = http.requestMatchers();
        this.systemInterfaceService.getAllByWhiteIsTrueAndActiveTrueAndSystemTrue().forEach(v -> {
            List<SystemMethodModel> methods = v.getMethods();
            for (SystemMethodModel method : methods) {
                try {
                    configurer.and().authorizeRequests().antMatchers(getMethod(method.getMethod()), v.getPath()).permitAll();
                } catch (Exception e) {
                    log.error("authorize request error", e.getMessage());
                }
            }
        });
        configurer.and().authorizeRequests().antMatchers("/**").authenticated();
    }

    /**
     * get http method form db
     *
     * @param method method string
     * @return http method
     */
    private HttpMethod getMethod(String method) {
        HttpMethod httpMethod = null;
        switch (method.toLowerCase()) {
            case "get":
                httpMethod = HttpMethod.GET;
                break;
            case "post":
                httpMethod = HttpMethod.POST;
                break;
            case "put":
                httpMethod = HttpMethod.PUT;
                break;
            case "delete":
                httpMethod = HttpMethod.DELETE;
                break;
        }
        return httpMethod;
    }

}
