package com.rapido.communication_service.dto;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class SignalMessage {

    private String type;

    private Long senderId;

    private Long receiverId;

    private String sdp;

    private String candidate;
}