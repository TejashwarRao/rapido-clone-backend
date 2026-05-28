package com.rapido.communication_service.service;

import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber {

    public void receiveMessage(
            String message
    ) {

        System.out.println(
                "MESSAGE RECEIVED FROM REDIS: "
                        + message
        );
    }
}
