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

import com.bootstack.common.enums.SystemMessageEnums;
import com.bootstack.model.system.interfaces.SystemInterfaceModel;
import com.bootstack.model.system.method.SystemMethodModel;
import com.bootstack.service.system.interfaces.SystemInterfaceService;
import com.bootstack.service.system.method.SystemMethodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * <p> SecurityAccessDecisionManager </p>
 * <p> Description : SecurityAccessDecisionManager </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 15:51 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Slf4j
@Service(value = "securityAccessDecisionManager")
public class SecurityAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    private SystemInterfaceService systemInterfaceService;

    @Autowired
    private SystemMethodService systemMethodService;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String requestUrl = request.getServletPath(), requestMethod = request.getMethod();
        log.info("current api interface：" + requestUrl + " , request method：" + requestMethod);
        log.info("database api interface {}", requestUrl);
        // get method info from db
        SystemMethodModel systemMethodModel = this.systemMethodService.getByMethod(requestMethod.toUpperCase());
        // Get whether the data is in the white list through the database
//        List<SystemInterfaceModel> systemInterfaces = IteratorUtils.toList(this.systemInterfaceService.getAllByPathLike(requestUrl).iterator());
        if (!ObjectUtils.isEmpty(systemMethodModel)) {
            SystemInterfaceModel systemInterfaceModel = this.systemInterfaceService.getByPathLikeAndMethods(requestUrl, systemMethodModel);
            if (!ObjectUtils.isEmpty(systemInterfaceModel)) {
                return;
            }
        }
        throw new AccessDeniedException(SystemMessageEnums.SYSTEM_UNAUTHORIZED.getValue());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
