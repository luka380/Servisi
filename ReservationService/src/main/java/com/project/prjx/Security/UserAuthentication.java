package com.project.prjx.Security;

import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Builder
public class UserAuthentication implements Authentication {
    private final BaseUserDto user;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String validationMethod;
    private final Boolean isAuthenticated;
    private final Boolean isEnabled;
    private final String token;

    public UserAuthentication(BaseUserDto user, String username, Collection<? extends GrantedAuthority> authorities, String validationMethod, Boolean isAuthenticated, Boolean isEnabled, String token) {
        this.user = user;
        this.username = username;
        this.authorities = authorities;
        this.validationMethod = validationMethod;
        this.isAuthenticated = isAuthenticated;
        this.isEnabled = isEnabled;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.copyOf(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public boolean equals(Object another) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String getName() {
        return username;
    }
}
