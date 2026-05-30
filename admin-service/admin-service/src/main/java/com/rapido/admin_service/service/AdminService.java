package com.rapido.admin_service.service;

import com.rapido.admin_service.dto.AdminAuthResponseDTO;
import com.rapido.admin_service.dto.AdminLoginRequestDTO;
import com.rapido.admin_service.dto.AdminRegisterRequestDTO;
import com.rapido.admin_service.entity.Admin;
import com.rapido.admin_service.repository.AdminRepository;
import com.rapido.admin_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Admin register(AdminRegisterRequestDTO request) {

        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Admin already exists with this email");
        }

        Admin admin = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .build();

        return adminRepository.save(admin);
    }

    public AdminAuthResponseDTO login(AdminLoginRequestDTO request) {

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!admin.getActive()) {
            throw new RuntimeException("Admin account is inactive");
        }

        String token = jwtUtil.generateToken(admin.getEmail());

        return new AdminAuthResponseDTO(token, admin.getRole().name());
    }
}