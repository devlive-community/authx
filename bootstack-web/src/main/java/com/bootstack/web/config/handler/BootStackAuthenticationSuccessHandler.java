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
package com.bootstack.web.config.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootstack.cache.CacheManager;
import com.bootstack.web.BootStackWebSupport;
import com.bootstack.web.entity.RemoteServerEntity;
import com.bootstack.web.template.HttpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> BootStackAuthenticationSuccessHandler </p>
 * <p> Description : BootStackAuthenticationSuccessHandler </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 19:43 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Component(value = "bootStackAuthenticationSuccessHandler")
public class BootStackAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessPath = request.getServletPath();
        HttpSession session = request.getSession();
        RemoteServerEntity remoteServer = new RemoteServerEntity(environment);
        Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("Authorization", "Bearer " + cacheManager.get(BootStackWebSupport.CACHE_AUTHENTICATION_TOKEN).getValue());
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        String path = remoteServer.fullPath() + "user/info/" + authentication.getPrincipal();
        String userJson = httpTemplate.getRemoteResponseToString(path, headers);
        JSONObject userInfo = JSON.parseObject(userJson).getJSONObject("data");
        // 将授权用户信息抽取到session中
        session.setAttribute("userInfo", userInfo);
        if (accessPath.equals(BootStackWebSupport.AUTHENTICATION_LOGIN)) {
            response.sendRedirect(BootStackWebSupport.COMMON_WHITE_LIST_USER_REGISTER);
        } else {
            response.sendRedirect(accessPath);
        }

    }

}