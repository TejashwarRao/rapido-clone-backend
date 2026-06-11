package com.rapido.auth_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OidcClaimsConfig {

    @Bean
    public String oidcConfig() {
        return "OIDC Enabled";
    }
}