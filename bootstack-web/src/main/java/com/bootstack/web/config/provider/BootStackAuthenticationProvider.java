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
package com.bootstack.web.config.provider;

import com.bootstack.cache.Cache;
import com.bootstack.cache.CacheManager;
import com.bootstack.web.BootStackWebSupport;
import com.bootstack.web.entity.BadCredentialsEntity;
import com.bootstack.web.entity.JwtTokenEntity;
import com.bootstack.web.entity.RemoteServerEntity;
import com.bootstack.web.entity.SuccessCredentialsEntity;
import com.bootstack.web.template.JwtTemplate;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p> BootStackAuthenticationProvider </p>
 * <p> Description : BootStackAuthenticationProvider </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 19:33 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Component(value = "bootStackAuthenticationProvider")
public class BootStackAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Environment environment;

    @Autowired
    private Gson gson;

    @Autowired
    private CloseableHttpClient client;

    @Autowired
    private JwtTemplate jwtTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String encoding = environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".encoding");
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        // use rest oauth2
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        String param = "?username=" + username + "&password=" + password
                + "&grant_type=" + remoteServer.getApiGrantType()
                + "&client_id=" + remoteServer.getApiClientId();
        HttpPost post = new HttpPost(remoteServer.oauthPath() + param);
        post.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.setHeader("Authorization", "Basic d2lraWZ0LWNsaWVudDp3aWtpZnQtd2Vi");
        try {
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            if (!ObjectUtils.isEmpty(entity)) {
                String result = EntityUtils.toString(entity, encoding);
                // error
                BadCredentialsEntity badCredentials = gson.fromJson(result, BadCredentialsEntity.class);
                if (ObjectUtils.isEmpty(badCredentials.getError())) {
                    System.out.println(gson.toJson(result));
                    // success
                    SuccessCredentialsEntity successCredentials = gson.fromJson(result, SuccessCredentialsEntity.class);
                    JwtTokenEntity jwtToken = (JwtTokenEntity) jwtTemplate.decodedJwtTokenBody(successCredentials.getAccess_token(), JwtTokenEntity.class);
                    List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
                    Arrays.asList(jwtToken.getAuthorities()).forEach(grant -> {
                        grantedAuthoritys.add(new SimpleGrantedAuthority("ROLE_" + grant));
                    });
                    Cache cache = new Cache();
                    cache.setKey(BootStackWebSupport.CACHE_AUTHENTICATION_TOKEN);
                    cache.setValue(successCredentials.getAccess_token());
                    cache.setTimeOut(0);
                    cacheManager.put(BootStackWebSupport.CACHE_AUTHENTICATION_TOKEN, cache);
                    return new UsernamePasswordAuthenticationToken(username, password, grantedAuthoritys);
                } else {
                    System.out.println(gson.toJson(result));
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}