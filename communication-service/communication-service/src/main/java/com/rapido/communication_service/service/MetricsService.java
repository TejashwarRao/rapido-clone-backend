package com.rapido.communication_service.service;

import io.micrometer.core.instrument.Counter;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final Counter
            websocketMessages;

    private final Counter
            kafkaMessages;

    private final Counter
            redisMessages;

    public MetricsService(
            MeterRegistry meterRegistry
    ) {

        websocketMessages =
                meterRegistry.counter(
                        "websocket.messages"
                );

        kafkaMessages =
                meterRegistry.counter(
                        "kafka.messages"
                );

        redisMessages =
                meterRegistry.counter(
                        "redis.messages"
                );
    }

    public void incrementWebsocketMessages() {

        websocketMessages.increment();
    }

    public void incrementKafkaMessages() {

        kafkaMessages.increment();
    }

    public void incrementRedisMessages() {

        redisMessages.increment();
    }
}
