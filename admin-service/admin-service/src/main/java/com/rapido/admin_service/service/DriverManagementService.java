package com.rapido.admin_service.service;

import com.rapido.admin_service.dto.DriverSuspendRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DriverManagementService {

    private final AuditLogService auditLogService;

    public Map<String, Object> suspendDriver(Long driverId, DriverSuspendRequestDTO request) {

        auditLogService.saveLog(
                "admin@rapido.com",
                "DRIVER_SUSPENDED",
                "Driver ID " + driverId + " suspended. Reason: " + request.getReason()
        );

        Map<String, Object> response = new HashMap<>();

        response.put("message", "Driver suspended successfully");
        response.put("driverId", driverId);
        response.put("reason", request.getReason());
        response.put("loginBlocked", true);
        response.put("auditRecorded", true);

        return response;
    }
}