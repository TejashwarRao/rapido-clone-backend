package com.rapido.communication_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {

    private Long rideId;

    private Long senderId;

    private Long receiverId;

    private String message;

    private MessageType type;
}