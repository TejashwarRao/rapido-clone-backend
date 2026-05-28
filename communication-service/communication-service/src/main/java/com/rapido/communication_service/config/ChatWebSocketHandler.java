package com.rapido.communication_service.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.rapido.communication_service.dto.SignalMessage;
import com.rapido.communication_service.entity.ChatMessage;
import com.rapido.communication_service.repository.ChatMessageRepository;
import com.rapido.communication_service.service.KafkaProducerService;
import com.rapido.communication_service.service.MetricsService;
import com.rapido.communication_service.service.RedisPublisher;
import com.rapido.communication_service.store.OnlineUserStore;

import org.springframework.stereotype.Component;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ChatWebSocketHandler
        extends TextWebSocketHandler {

    private static final Set<WebSocketSession>
            sessions =
            new HashSet<>();

    private final ChatMessageRepository
            chatMessageRepository;

    private final OnlineUserStore
            onlineUserStore;

    private final KafkaProducerService
            kafkaProducerService;

    private final RedisPublisher
            redisPublisher;

    private final MetricsService
            metricsService;

    private final ObjectMapper
            objectMapper =
            new ObjectMapper();

    public ChatWebSocketHandler(

            ChatMessageRepository
                    chatMessageRepository,

            OnlineUserStore
                    onlineUserStore,

            KafkaProducerService
                    kafkaProducerService,

            RedisPublisher
                    redisPublisher,

            MetricsService
                    metricsService
    ) {

        this.chatMessageRepository =
                chatMessageRepository;

        this.onlineUserStore =
                onlineUserStore;

        this.kafkaProducerService =
                kafkaProducerService;

        this.redisPublisher =
                redisPublisher;

        this.metricsService =
                metricsService;
    }

    @Override
    public void afterConnectionEstablished(
            WebSocketSession session
    ) {

        sessions.add(session);

        System.out.println(
                "CLIENT CONNECTED"
        );
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message
    ) throws IOException {

        // INCREMENT WEBSOCKET METRIC

        metricsService
                .incrementWebsocketMessages();

        String payload =
                message.getPayload();

        System.out.println(
                "MESSAGE RECEIVED: "
                        + payload
        );

        JsonNode jsonNode =
                objectMapper.readTree(
                        payload
                );

        // ONLINE EVENT

        if (jsonNode.has("online")) {

            Long userId =
                    jsonNode.get("userId")
                            .asLong();

            onlineUserStore.addUser(
                    userId,
                    session.getId()
            );

            Map<String, Object>
                    response =
                    new HashMap<>();

            response.put(
                    "userId",
                    userId
            );

            response.put(
                    "status",
                    "ONLINE"
            );

            broadcast(
                    objectMapper
                            .writeValueAsString(
                                    response
                            )
            );

            return;
        }

        // TYPING EVENT

        if (jsonNode.has("typing")) {

            broadcast(payload);

            return;
        }

        // WEBRTC SIGNALING

        if (
                jsonNode.has("type")
                        &&
                        (
                                jsonNode.get("type")
                                        .asText()
                                        .equals("OFFER")

                                        ||

                                        jsonNode.get("type")
                                                .asText()
                                                .equals("ANSWER")

                                        ||

                                        jsonNode.get("type")
                                                .asText()
                                                .equals("ICE")
                        )
        ) {

            SignalMessage signalMessage =
                    objectMapper.readValue(
                            payload,
                            SignalMessage.class
                    );

            System.out.println(
                    "WEBRTC SIGNAL: "
                            + signalMessage.getType()
            );

            broadcast(payload);

            return;
        }

        // NORMAL CHAT MESSAGE

        ChatMessage chatMessage =
                objectMapper.readValue(
                        payload,
                        ChatMessage.class
                );

        chatMessage.setTimestamp(
                LocalDateTime.now()
        );

        chatMessage.setDelivered(true);

        chatMessage.setSeen(false);

        // SAVE TO DATABASE

        chatMessageRepository.save(
                chatMessage
        );

        // SEND TO KAFKA

        kafkaProducerService.sendMessage(
                payload
        );

        // PUBLISH TO REDIS

        redisPublisher.publish(
                payload
        );

        // BROADCAST TO CLIENTS

        broadcast(payload);
    }

    private void broadcast(
            String message
    ) throws IOException {

        for (WebSocketSession s : sessions) {

            s.sendMessage(
                    new TextMessage(
                            message
                    )
            );
        }
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status
    ) throws Exception {

        sessions.remove(session);

        Long disconnectedUser = null;

        for (
                Map.Entry<Long, String> entry
                :
                onlineUserStore
                        .getOnlineUsers()
                        .entrySet()
        ) {

            if (
                    entry.getValue()
                            .equals(
                                    session.getId()
                            )
            ) {

                disconnectedUser =
                        entry.getKey();

                break;
            }
        }

        if (disconnectedUser != null) {

            onlineUserStore.removeUser(
                    disconnectedUser
            );

            Map<String, Object>
                    response =
                    new HashMap<>();

            response.put(
                    "userId",
                    disconnectedUser
            );

            response.put(
                    "status",
                    "OFFLINE"
            );

            broadcast(
                    objectMapper
                            .writeValueAsString(
                                    response
                            )
            );
        }

        System.out.println(
                "CLIENT DISCONNECTED"
        );
    }
}