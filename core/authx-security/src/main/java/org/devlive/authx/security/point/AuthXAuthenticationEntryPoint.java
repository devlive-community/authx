package org.devlive.authx.security.point;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.devlive.authx.common.enums.SystemMessageEnums;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AuthXAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", SystemMessageEnums.SYSTEM_ERROR_TOKEN.getCode());
        map.put("message", SystemMessageEnums.SYSTEM_ERROR_TOKEN.getValue());
        response.setContentType("application/json");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}