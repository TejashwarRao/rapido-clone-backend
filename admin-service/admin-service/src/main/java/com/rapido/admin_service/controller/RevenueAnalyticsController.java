package com.rapido.admin_service.controller;

import com.rapido.admin_service.dto.RevenueAnalyticsDTO;
import com.rapido.admin_service.service.RevenueAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/analytics")
@RequiredArgsConstructor
public class RevenueAnalyticsController {

    private final RevenueAnalyticsService revenueAnalyticsService;

    @GetMapping("/revenue")
    public ResponseEntity<RevenueAnalyticsDTO> getRevenueAnalytics() {
        return ResponseEntity.ok(revenueAnalyticsService.getRevenueAnalytics());
    }
}