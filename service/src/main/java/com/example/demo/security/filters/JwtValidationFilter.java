package com.example.demo.security.filters;

import com.example.demo.models.dtos.UserSecurityDto;
import com.example.demo.services.utils.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtValidationFilter extends BasicAuthenticationFilter {
    private final JwtService jwtService;
    public JwtValidationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        String BEARER = "Bearer ";
        if(header == null || !header.startsWith(BEARER)){
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(BEARER, "");
        try {
            UserSecurityDto userSecurityDto = jwtService.validationToken(token);
            String username = userSecurityDto.getUsername();
            Collection<? extends GrantedAuthority> authorities = userSecurityDto.getAuthorities();
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }catch (Exception e){
            chain.doFilter(request, response);
        }
    }
}
