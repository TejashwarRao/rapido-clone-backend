package com.rapido.search.controller;

import com.rapido.search.recommendation.RecommendationService;
import com.rapido.search.recommendation.RideRecommendationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService service;

    @GetMapping("/rides")
    public List<RideRecommendationResponse> recommendations(
            @RequestParam Long userId) {

        return service.getRecommendations(userId);
    }
}