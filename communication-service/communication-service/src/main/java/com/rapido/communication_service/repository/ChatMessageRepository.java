package com.rapido.communication_service.repository;

import com.rapido.communication_service.entity.ChatMessage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository
        extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRideId(
            Long rideId
    );

    List<ChatMessage>
    findByReceiverIdAndSeenFalse(
            Long receiverId
    );
}