package com.rapido.notification_service.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.stereotype.Service;

@Service
public class WebSocketNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificationService(
            SimpMessagingTemplate messagingTemplate
    ) {

        this.messagingTemplate =
                messagingTemplate;
    }

    public void sendNotification(
            String destination,
            Object payload
    ) {

        messagingTemplate.convertAndSend(
                destination,
                payload
        );
    }
}