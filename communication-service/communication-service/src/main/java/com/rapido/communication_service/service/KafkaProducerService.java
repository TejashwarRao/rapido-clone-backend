package com.rapido.communication_service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String>
            kafkaTemplate;

    private final MetricsService
            metricsService;

    public void sendMessage(
            String message
    ) {

        kafkaTemplate.send(
                "chat-messages",
                message
        );

        metricsService
                .incrementKafkaMessages();

        System.out.println(
                "MESSAGE SENT TO KAFKA"
        );
    }
}