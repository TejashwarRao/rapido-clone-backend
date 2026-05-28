package com.rapido.communication_service.controller;

import com.rapido.communication_service.entity.ChatMessage;

import com.rapido.communication_service.service.ChatHistoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatHistoryService
            chatHistoryService;

    // GET CHAT HISTORY

    @GetMapping("/history/{rideId}")
    public List<ChatMessage>
    getRideHistory(
            @PathVariable Long rideId
    ) {

        return chatHistoryService
                .getRideMessages(
                        rideId
                );
    }

    // GET UNREAD MESSAGES

    @GetMapping("/unread/{receiverId}")
    public List<ChatMessage>
    getUnreadMessages(
            @PathVariable Long receiverId
    ) {

        return chatHistoryService
                .getUnreadMessages(
                        receiverId
                );
    }
}