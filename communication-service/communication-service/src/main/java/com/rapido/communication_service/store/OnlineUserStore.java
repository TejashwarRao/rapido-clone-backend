package com.rapido.communication_service.store;

import org.springframework.stereotype.Component;

import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class OnlineUserStore {

    // userId -> sessionId

    private final Map<Long, String>
            onlineUsers =
            new ConcurrentHashMap<>();

    public void addUser(
            Long userId,
            String sessionId
    ) {

        onlineUsers.put(
                userId,
                sessionId
        );
    }

    public void removeUser(
            Long userId
    ) {

        onlineUsers.remove(
                userId
        );
    }

    public boolean isOnline(
            Long userId
    ) {

        return onlineUsers.containsKey(
                userId
        );
    }

    public Map<Long, String> getOnlineUsers() {

        return onlineUsers;
    }
}
