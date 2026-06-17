package com.rapido.search.security;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SearchRateLimiter {

    private final AtomicInteger counter =
            new AtomicInteger();

    public boolean allowRequest() {

        return counter.incrementAndGet() <= 100;
    }
}