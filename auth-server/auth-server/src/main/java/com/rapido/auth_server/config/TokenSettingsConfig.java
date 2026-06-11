package com.rapido.auth_server.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

@Configuration
public class TokenSettingsConfig {

    @Bean
    public TokenSettings tokenSettings() {

        return TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofMinutes(15))
                .refreshTokenTimeToLive(Duration.ofDays(7))
                .reuseRefreshTokens(false)
                .build();
    }
}