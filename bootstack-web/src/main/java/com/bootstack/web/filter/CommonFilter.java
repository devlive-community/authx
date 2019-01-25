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
package com.bootstack.web.filter;

import com.bootstack.cache.Cache;
import com.bootstack.cache.CacheManager;
import com.bootstack.web.BootStackWebSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> CommonFilter </p>
 * <p> Description : CommonFilter </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-26 02:00 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Component
public class CommonFilter implements Filter {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        request = (HttpServletRequest) servletRequest;
        response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURI();
        Cache cache = cacheManager.get(BootStackWebSupport.CACHE_AUTHENTICATION_TOKEN);
        if (ObjectUtils.isEmpty(cache)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // if user login redirect to home
            if (requestUrl.contains(BootStackWebSupport.AUTHENTICATION_LOGIN) || requestUrl.contains(BootStackWebSupport.AUTHENTICATION_REGISTER)) {
                response.sendRedirect(BootStackWebSupport.REDIRECT_HOME);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {
    }

}