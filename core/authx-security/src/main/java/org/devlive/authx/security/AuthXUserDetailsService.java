package org.devlive.authx.security;

import lombok.extern.slf4j.Slf4j;
import org.devlive.authx.service.entity.UserEntity;
import org.devlive.authx.service.service.UserIService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AuthXUserDetailsService implements UserDetailsService {

    private final UserIService userService;

    public AuthXUserDetailsService(UserIService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // Encapsulating permission information
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserEntity user = this.userService.getModelByName(s);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException(String.format("this user %s not found", s));
        }
        // set user roles
        user.getRoles().forEach(v -> authorities.add(new SimpleGrantedAuthority(String.valueOf(v.getId()))));
        // generate a test user
        return new User(user.getName(), user.getPassword(), authorities);
    }
}
