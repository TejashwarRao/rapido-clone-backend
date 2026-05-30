package com.rapido.admin_service.controller;

import com.rapido.admin_service.dto.MonitoringMetricsDTO;
import com.rapido.admin_service.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/monitoring")
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @GetMapping("/metrics")
    public ResponseEntity<MonitoringMetricsDTO> getMonitoringMetrics() {
        return ResponseEntity.ok(monitoringService.getMonitoringMetrics());
    }
}
