package com.rapido.ride_service.controller;

import com.rapido.ride_service.service.EventReplayService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventReplayController {

    private final EventReplayService
            eventReplayService;

    @PostMapping(
            "/replay/{eventId}"
    )
    public String replayEvent(
            @PathVariable String eventId
    ) {

        return eventReplayService
                .replayEvent(
                        eventId
                );
    }
}