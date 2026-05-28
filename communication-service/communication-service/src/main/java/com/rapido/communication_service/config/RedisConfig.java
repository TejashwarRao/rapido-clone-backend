package com.rapido.communication_service.config;

import com.rapido.communication_service.service.RedisSubscriber;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.listener.ChannelTopic;

import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Bean
    public ChannelTopic topic() {

        return new ChannelTopic(
                "chat-channel"
        );
    }

    @Bean
    public RedisTemplate<String, Object>
    redisTemplate(
            RedisConnectionFactory connectionFactory
    ) {

        RedisTemplate<String, Object>
                template =
                new RedisTemplate<>();

        template.setConnectionFactory(
                connectionFactory
        );

        return template;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(
            RedisSubscriber subscriber
    ) {

        return new MessageListenerAdapter(
                subscriber,
                "receiveMessage"
        );
    }

    @Bean
    RedisMessageListenerContainer
    redisContainer(

            RedisConnectionFactory
                    connectionFactory,

            MessageListenerAdapter
                    listenerAdapter,

            ChannelTopic
                    topic
    ) {

        RedisMessageListenerContainer
                container =
                new RedisMessageListenerContainer();

        container.setConnectionFactory(
                connectionFactory
        );

        container.addMessageListener(
                listenerAdapter,
                topic
        );

        return container;
    }
}