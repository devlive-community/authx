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
import com.bootstack.model.system.role.SystemRoleModel;
import com.bootstack.service.system.interfaces.SystemInterfaceService;
import com.bootstack.service.system.method.SystemMethodService;
import com.bootstack.service.system.role.SystemRoleService;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    @Autowired
    private SystemRoleService systemRoleService;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String requestUrl = request.getServletPath(), requestMethod = request.getMethod();
        log.info("current api interface：" + requestUrl + " , request method：" + requestMethod);
        // get method info from db
        SystemMethodModel systemMethodModel = this.systemMethodService.getByMethod(requestMethod.toUpperCase());
        // Get whether the data is in the white list through the database
        if (!ObjectUtils.isEmpty(systemMethodModel)) {
            SystemInterfaceModel systemInterfaceModel = this.systemInterfaceService.getByPathAndMethodsIn(requestUrl, systemMethodModel);
            if (!ObjectUtils.isEmpty(systemInterfaceModel)) {
                return;
            }
        }
        // 用户个人权限查询
        Map<Long, Role> menus = new ConcurrentHashMap<>();
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            // TODO：抽取权限过来的数据并解析(目前通过数据库抽取后期加入缓冲中)
            String granted = grantedAuthority.getAuthority();
            SystemRoleModel roleModel = this.systemRoleService.getModelById(Long.valueOf(granted));
            roleModel.getMenuList().forEach(m -> {
                if (ObjectUtils.isEmpty(menus.get(m.getId()))) {
                    if (!m.getUrl().equalsIgnoreCase("#")) {
                        Role role = new Role();
                        role.setUrl(m.getUrl());
                        role.setMethod(StringUtils.join(m.getMethods().stream().map(v -> v.getMethod()).collect(Collectors.toList()), ","));
                        menus.put(m.getId(), role);
                    }
                }
            });
        }
        // 抽取判断授权的菜单列表
        for (Long k : menus.keySet()) {
            Role role = menus.get(k);
            if (role.getUrl().contains("*")) {
                if (requestMethod.contains(role.getMethod()) && requestUrl.startsWith(role.getUrl().replace("*", ""))) {
                    return;
                }
            } else {
                if (requestMethod.contains(role.getMethod()) && requestUrl.equalsIgnoreCase(role.getUrl())) {
                    return;
                }
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

    @Data
    @ToString
    private class Role {

        private String url;
        private String method;

    }

}
