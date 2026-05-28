package com.rapido.communication_service.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Getter
@Setter
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rideId;

    private Long senderId;

    private Long receiverId;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime timestamp;

    // NEW FIELDS

    private Boolean delivered;

    private Boolean seen;
}