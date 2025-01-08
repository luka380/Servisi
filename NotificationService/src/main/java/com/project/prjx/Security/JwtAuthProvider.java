package com.project.prjx.Security;

import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Security.Tokens.JwtToken;
import com.project.prjx.Services.BaseClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthProvider implements AuthenticationProvider {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private BaseClientServiceInterface<BaseUserDto> userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();

        if (jwtUtils.isTokenValid(token)) {
            BaseUserDto client = userService.getUserByUsername(jwtUtils.extractName(token));


            if (client == null || client.getEmail().email().equals(jwtUtils.extractEmail(token)) || !client.getIsEnabled()) {
                return null;
            }

            return UserAuthentication.builder()
                    .isAuthenticated(true)
                    .user(client)
                    .username(client.getUsername())
                    .validationMethod("jwtToken")
                    .authorities(List.of(new SimpleGrantedAuthority(client.getRole())))
                    .isEnabled(client.getIsEnabled())
                    .build();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtToken.class);
    }
}
