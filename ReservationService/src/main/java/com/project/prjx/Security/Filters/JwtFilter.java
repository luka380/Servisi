package com.project.prjx.Security.Filters;

import com.project.prjx.Security.JwtUtils;
import com.project.prjx.Security.Tokens.JwtToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = getToken(request);

        if (token.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtToken jwtToken = new JwtToken(token.get());
            SecurityContextHolder.getContext().setAuthentication(jwtToken);
            Authentication auth = authenticationManager.authenticate(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    protected Optional<String> getToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return Optional.empty();
        }
        return Optional.of(authHeader.split(" ")[1]);
    }
}