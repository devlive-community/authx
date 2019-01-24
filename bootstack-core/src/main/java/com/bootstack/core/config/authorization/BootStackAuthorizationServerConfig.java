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

import com.bootstack.core.config.authorization.enhancer.BootStackTokenEnhancer;
import com.bootstack.core.config.authorization.exception.BootStackOauthWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * <p> BootStackAuthorizationServerConfig </p>
 * <p> Description : BootStackAuthorizationServerConfig </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 14:25 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Configuration
@EnableAuthorizationServer
public class BootStackAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BootStackOauthWebResponseExceptionTranslator bootStackOauthWebResponseExceptionTranslator;

    @Autowired
    private BootStackTokenEnhancer bootStackTokenEnhancer;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(BootStackAuthorizationOauth2Support.SECURITY_CLIENT_ID)
                .secret(BootStackAuthorizationOauth2Support.SECURITY_CLIENT_SECRET)
                .authorizedGrantTypes(BootStackAuthorizationOauth2Support.SECURITY_GRANT_TYPES)
                .scopes("select", "write", "read")
                .resourceIds(BootStackAuthorizationOauth2Support.SECURITY_RESOURCE_ID);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        // custom token
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter, bootStackTokenEnhancer));
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
        // custom response
        endpoints.exceptionTranslator(bootStackOauthWebResponseExceptionTranslator);
    }

}