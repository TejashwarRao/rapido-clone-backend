package com.rapido.auth_server.config;

import java.time.Duration;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;

import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

@Configuration
public class RegisteredClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient registeredClient =
                RegisteredClient.withId(UUID.randomUUID().toString())

                        .clientId("rapido-client")

                        .clientSecret("{noop}secret")

                        .clientAuthenticationMethod(
                                ClientAuthenticationMethod.CLIENT_SECRET_BASIC)

                        .authorizationGrantType(
                                AuthorizationGrantType.AUTHORIZATION_CODE)

                        .authorizationGrantType(
                                AuthorizationGrantType.REFRESH_TOKEN)

                        .authorizationGrantType(
                                AuthorizationGrantType.CLIENT_CREDENTIALS)

                        .redirectUri(
                                "http://127.0.0.1:8080/login/oauth2/code/rapido-client")

                        .scope("openid")
                        .scope("ride.read")
                        .scope("ride.write")
                        .scope("payment.read")
                        .scope("payment.write")
                        .scope("admin.read")
                        .scope("admin.write")

                        .tokenSettings(tokenSettings())

                        .build();

        return new InMemoryRegisteredClientRepository(
                registeredClient);
    }

    private TokenSettings tokenSettings() {

        return TokenSettings.builder()
                .accessTokenTimeToLive(
                        Duration.ofMinutes(15))

                .refreshTokenTimeToLive(
                        Duration.ofDays(7))

                .reuseRefreshTokens(false)

                .build();
    }
}