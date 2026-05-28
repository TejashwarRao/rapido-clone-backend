package com.rapido.communication_service.service;

import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "chat-messages",
            groupId = "communication-group"
    )
    public void consume(
            String message
    ) {

        System.out.println(
                "MESSAGE RECEIVED FROM KAFKA: "
                        + message
        );
    }
}