package org.devlive.authx.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = AuthXOauthExceptionSerializer.class)
public class AuthXOauthException extends OAuth2Exception {
    public AuthXOauthException(String msg) {
        super(msg);
    }
}
