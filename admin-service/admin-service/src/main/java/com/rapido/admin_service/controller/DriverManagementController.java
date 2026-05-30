package com.rapido.admin_service.controller;

import com.rapido.admin_service.dto.DriverSuspendRequestDTO;
import com.rapido.admin_service.service.DriverManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/drivers")
@RequiredArgsConstructor
public class DriverManagementController {

    private final DriverManagementService driverManagementService;

    @PutMapping("/{id}/suspend")
    public ResponseEntity<Map<String, Object>> suspendDriver(
            @PathVariable Long id,
            @RequestBody DriverSuspendRequestDTO request) {

        return ResponseEntity.ok(
                driverManagementService.suspendDriver(id, request)
        );
    }
}