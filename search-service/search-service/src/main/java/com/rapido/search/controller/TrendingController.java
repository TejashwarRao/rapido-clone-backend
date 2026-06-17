package com.rapido.search.controller;

import com.rapido.search.analytics.TrendingDestinationResponse;
import com.rapido.search.analytics.TrendingDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class TrendingController {

    private final TrendingDestinationService service;

    @GetMapping("/trending")
    public List<TrendingDestinationResponse> trending() {

        return service.getTrending();
    }
}