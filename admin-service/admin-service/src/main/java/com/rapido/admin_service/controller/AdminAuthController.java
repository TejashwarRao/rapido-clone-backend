package com.rapido.admin_service.controller;

import com.rapido.admin_service.dto.AdminAuthResponseDTO;
import com.rapido.admin_service.dto.AdminLoginRequestDTO;
import com.rapido.admin_service.dto.AdminRegisterRequestDTO;
import com.rapido.admin_service.entity.Admin;
import com.rapido.admin_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AdminRegisterRequestDTO request) {
        Admin admin = adminService.register(request);
        return ResponseEntity.ok("Admin registered successfully with role: " + admin.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity<AdminAuthResponseDTO> login(@RequestBody AdminLoginRequestDTO request) {
        AdminAuthResponseDTO response = adminService.login(request);
        return ResponseEntity.ok(response);
    }
}