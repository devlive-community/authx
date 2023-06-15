package org.devlive.authx.security.authorization;

import org.devlive.authx.security.enhancer.AuthXTokenEnhancer;
import org.devlive.authx.security.exception.AuthXOauthWebResponseExceptionTranslator;
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

@Configuration
@EnableAuthorizationServer
public class AuthXServerConfigure extends AuthorizationServerConfigurerAdapter {

    private final JwtAccessTokenConverter accessTokenConverter;
    private final TokenStore tokenStore;
    private final AuthenticationManager authenticationManager;
    private final AuthXOauthWebResponseExceptionTranslator oauthWebResponseExceptionTranslator;
    private final AuthXTokenEnhancer authXTokenEnhancer;

    public AuthXServerConfigure(JwtAccessTokenConverter accessTokenConverter, TokenStore tokenStore, AuthenticationManager authenticationManager, AuthXOauthWebResponseExceptionTranslator oauthWebResponseExceptionTranslator, AuthXTokenEnhancer authXTokenEnhancer) {
        this.accessTokenConverter = accessTokenConverter;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
        this.oauthWebResponseExceptionTranslator = oauthWebResponseExceptionTranslator;
        this.authXTokenEnhancer = authXTokenEnhancer;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(AuthXOauth2Support.SECURITY_CLIENT_ID)
                // The secret password configuration must be filled in the format {bcrypt}+ encrypted password starting with Spring Security 5.0
//                .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(BootStackAuthorizationOauth2Support.SECURITY_CLIENT_SECRET))
                .secret(AuthXOauth2Support.SECURITY_CLIENT_SECRET)
                .authorizedGrantTypes(AuthXOauth2Support.SECURITY_GRANT_TYPES)
                .scopes("select", "write", "read")
                .resourceIds(AuthXOauth2Support.SECURITY_RESOURCE_ID);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        // custom token
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter, authXTokenEnhancer));
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(oauthWebResponseExceptionTranslator);
    }
}
