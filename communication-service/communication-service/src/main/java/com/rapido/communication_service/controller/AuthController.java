package com.rapido.communication_service.controller;

import com.rapido.communication_service.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @GetMapping("/token")
    public String generateToken(
            @RequestParam String username
    ) {

        return jwtUtil.generateToken(
                username
        );
    }
}
