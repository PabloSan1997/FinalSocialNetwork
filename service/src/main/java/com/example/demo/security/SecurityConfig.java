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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    CommandLineRunner commandLineRunner(InitialValues initialValues){
        return  args -> {
            initialValues.viewAndGeneratedRoles();
        };
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedHeaders(List.of("Content-Type", "Authorization"));
        config.setAllowCredentials(true);
        config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> generateEndPointsAuth() {
        return auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/user/login", "/api/user/register").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/api/images",
                        "/api/user/userInfo",
                        "/api/images",
                        "/api/images/user",
                        "/api/images/{id}",
                        "/api/user/otherUser",
                        "/api/user/follow",
                        "/api/user/follow/**",
                        "/api/user/search/{username}"
                ).hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/api/images/{id}", "/api/comments/{id}", "/api/user/disable").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/user/logout", "/api/images", "/api/comments", "/api/image/like/{id}", "/follow/{id}").hasRole("USER")
                .requestMatchers("/", "/assets", "index.html", "/assets/**").permitAll()
                .anyRequest().authenticated();
    }
}
