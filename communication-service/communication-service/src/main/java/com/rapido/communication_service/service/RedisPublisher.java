package com.rapido.communication_service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.listener.ChannelTopic;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object>
            redisTemplate;

    private final ChannelTopic
            topic;

    private final MetricsService
            metricsService;

    public void publish(
            String message
    ) {

        redisTemplate.convertAndSend(
                topic.getTopic(),
                message
        );

        metricsService
                .incrementRedisMessages();

        System.out.println(
                "MESSAGE PUBLISHED TO REDIS"
        );
    }
}