package com.example.demo.security;

import com.example.demo.security.filters.JwtValidationFilter;
import com.example.demo.services.utils.InitialValues;
import com.example.demo.services.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtService jwtService) throws Exception{
        http.csrf(c -> c.disable())
                .authorizeHttpRequests(generateEndPointsAuth())
                .addFilter(new JwtValidationFilter(authenticationManager(), jwtService))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    CommandLineRunner commandLineRunner(InitialValues initialValues){
        return  args -> {
            initialValues.viewAndGeneratedRoles();
        };
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> generateEndPointsAuth() {
        return auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/user/login", "/app/user/register").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/api/images",
                        "/api/user/userInfo",
                        "/api/images",
                        "/api/images/user",
                        "/api/images/{id}",
                        "/api/user/otherUser"
                ).hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/api/images/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/user/logout", "/api/images").hasRole("USER")
                .anyRequest().permitAll();
    }
}
