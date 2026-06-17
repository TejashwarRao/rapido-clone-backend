package com.rapido.search.controller;

import com.rapido.search.analytics.RideAnalyticsResponse;
import com.rapido.search.analytics.RideAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class RideAnalyticsController {

    private final RideAnalyticsService service;

    @GetMapping("/rides")
    public RideAnalyticsResponse analytics(
            @RequestParam Long userId) {

        return service.analyze(userId);
    }
}