package com.rapido.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/public/**")
                        .permitAll()

                        .requestMatchers("/admin/**")
                        .hasRole("SUPER_ADMIN")

                        .requestMatchers("/finance/**")
                        .hasRole("FINANCE_ADMIN")

                        .anyRequest()
                        .authenticated()
                )

                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.jwt(
                                        Customizer.withDefaults()
                                )
                );

        return http.build();
    }
}