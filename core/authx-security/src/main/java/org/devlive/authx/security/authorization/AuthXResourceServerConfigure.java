package org.devlive.authx.security.authorization;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.security.handler.AuthXAccessDeniedHandler;
import org.devlive.authx.security.point.AuthXAuthenticationEntryPoint;
import org.devlive.authx.service.entity.system.method.SystemMethodModel;
import org.devlive.authx.service.service.system.interfaces.SystemInterfaceService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.List;

@Configuration
@EnableResourceServer
@Slf4j
public class AuthXResourceServerConfigure extends ResourceServerConfigurerAdapter {

    private final ResourceServerTokenServices resourceServerTokenServices;
    private final AuthXAccessDeniedHandler accessDeniedHandler;
    private final SystemInterfaceService systemInterfaceService;

    public AuthXResourceServerConfigure(ResourceServerTokenServices resourceServerTokenServices, AuthXAccessDeniedHandler accessDeniedHandler, SystemInterfaceService systemInterfaceService) {
        this.resourceServerTokenServices = resourceServerTokenServices;
        this.accessDeniedHandler = accessDeniedHandler;
        this.systemInterfaceService = systemInterfaceService;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(AuthXOauth2Support.SECURITY_RESOURCE_ID).tokenServices(resourceServerTokenServices)
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(new AuthXAuthenticationEntryPoint());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        HttpSecurity.RequestMatcherConfigurer configurer = http.requestMatchers();
        this.systemInterfaceService.getAllByWhiteIsTrueAndActiveTrueAndSystemTrue()
                .forEach(v -> {
                    List<SystemMethodModel> methods = v.getMethods();
                    for (SystemMethodModel method : methods) {
                        try {
                            configurer.and()
                                    .authorizeRequests()
                                    .antMatchers(getMethod(method.getMethod()), v.getPath())
                                    .permitAll();
                        } catch (Exception e) {
                            log.error("authorize request error", e.getMessage());
                        }
                    }
                });
        configurer.and()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated();
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
