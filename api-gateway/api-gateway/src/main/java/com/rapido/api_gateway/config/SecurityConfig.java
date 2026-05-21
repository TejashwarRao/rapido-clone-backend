package com.rapido.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        http

                // Disable CSRF
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // Configure authorization
                .authorizeExchange(exchanges -> exchanges

                        // Public APIs
                        .pathMatchers(
                                "/auth/**",
                                "/eureka/**",
                                "/actuator/**"
                        ).permitAll()

                        // All other APIs require authentication
                        .anyExchange().authenticated()
                )

                // Disable form login
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

                // Disable HTTP Basic auth
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable);

        return http.build();
    }
}