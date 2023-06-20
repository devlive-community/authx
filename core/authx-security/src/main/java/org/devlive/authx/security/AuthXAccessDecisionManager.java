package org.devlive.authx.security;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.devlive.authx.common.enums.SystemMessageEnums;
import org.devlive.authx.service.entity.system.interfaces.SystemInterfaceModel;
import org.devlive.authx.service.entity.system.method.SystemMethodModel;
import org.devlive.authx.service.entity.RoleEntity;
import org.devlive.authx.service.service.system.interfaces.SystemInterfaceService;
import org.devlive.authx.service.service.system.method.SystemMethodService;
import org.devlive.authx.service.service.RoleService;
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

@Slf4j
@Service
public class AuthXAccessDecisionManager implements AccessDecisionManager {

    private final SystemInterfaceService systemInterfaceService;
    private final SystemMethodService systemMethodService;
    private final RoleService systemRoleService;

    public AuthXAccessDecisionManager(SystemInterfaceService systemInterfaceService, SystemMethodService systemMethodService, RoleService systemRoleService) {
        this.systemInterfaceService = systemInterfaceService;
        this.systemMethodService = systemMethodService;
        this.systemRoleService = systemRoleService;
    }

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
            RoleEntity roleModel = this.systemRoleService.getModelById(Long.valueOf(granted));
            roleModel.getMenus().forEach(m -> {
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
                if (role.getMethod().contains(requestMethod) && requestUrl.startsWith(role.getUrl().replace("*", ""))) {
                    return;
                }
            } else {
                if (role.getMethod().contains(requestMethod) && requestUrl.equalsIgnoreCase(role.getUrl())) {
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
