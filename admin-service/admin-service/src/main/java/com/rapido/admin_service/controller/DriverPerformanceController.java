package com.rapido.admin_service.controller;

import com.rapido.admin_service.entity.DriverPerformance;
import com.rapido.admin_service.service.DriverPerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/drivers")
@RequiredArgsConstructor
public class DriverPerformanceController {

    private final DriverPerformanceService driverPerformanceService;

    @PostMapping("/performance")
    public ResponseEntity<DriverPerformance> saveDriverPerformance(
            @RequestBody DriverPerformance driverPerformance) {

        return ResponseEntity.ok(
                driverPerformanceService.saveDriverPerformance(driverPerformance)
        );
    }

    @GetMapping("/top-performing")
    public ResponseEntity<List<DriverPerformance>> getTopPerformingDrivers(
            @RequestParam(required = false) String region) {

        return ResponseEntity.ok(
                driverPerformanceService.getTopPerformingDrivers(region)
        );
    }
}
