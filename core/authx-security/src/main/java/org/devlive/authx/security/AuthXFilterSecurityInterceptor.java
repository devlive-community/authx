package org.devlive.authx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

@Service
public class AuthXFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private final FilterInvocationSecurityMetadataSource securityMetadataSource;

    public AuthXFilterSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Autowired
    public void setMyAccessDecisionManager(AuthXAccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation invocation = new FilterInvocation(request, response, chain);
        invoke(invocation);
    }

    private void invoke(FilterInvocation invocation) throws IOException, ServletException {
        // invocation url
        // call SecurityInvocationSecurityMetadataSource getAttributes(Object object) get invocation role
        // call SecurityAccessDecisionManager decide method validation role
        InterceptorStatusToken token = super.beforeInvocation(invocation);
        try {
            // execute next filter
            invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }


    @Override
    public void destroy() {
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}