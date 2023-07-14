package org.devlive.authx.security.authorization;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.security.handler.AuthXAccessDeniedHandler;
import org.devlive.authx.security.point.AuthXAuthenticationEntryPoint;
import org.devlive.authx.service.entity.MethodEntity;
import org.devlive.authx.service.repository.MenuRepository;
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
public class AuthXResourceServerConfigure extends ResourceServerConfigurerAdapter
{

    private final ResourceServerTokenServices resourceServerTokenServices;
    private final AuthXAccessDeniedHandler accessDeniedHandler;
    private final MenuRepository menuRepository;

    public AuthXResourceServerConfigure(ResourceServerTokenServices resourceServerTokenServices, AuthXAccessDeniedHandler accessDeniedHandler, MenuRepository menuRepository)
    {
        this.resourceServerTokenServices = resourceServerTokenServices;
        this.accessDeniedHandler = accessDeniedHandler;
        this.menuRepository = menuRepository;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(AuthXOauth2Support.SECURITY_RESOURCE_ID).tokenServices(resourceServerTokenServices)
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(new AuthXAuthenticationEntryPoint());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        HttpSecurity.RequestMatcherConfigurer configurer = http.requestMatchers();
        this.menuRepository.findAllByIsSystemIsTrue()
                .forEach(v -> {
                    List<MethodEntity> methods = v.getMethods();
                    for (MethodEntity method : methods) {
                        try {
                            configurer.and()
                                    .authorizeRequests()
                                    .antMatchers(getMethod(method.getMethod()), v.getUrl())
                                    .permitAll();
                        } catch (Exception e) {
                            log.error("Authorize request {} method {} error", method.getMethod(), v.getUrl(), e.getMessage());
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
