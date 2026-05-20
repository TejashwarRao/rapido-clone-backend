package com.rapido.auth_service.service;

import com.rapido.auth_service.dto.LoginRequest;
import com.rapido.auth_service.dto.RegisterRequest;

import com.rapido.auth_service.entity.User;

import com.rapido.auth_service.repository.UserRepository;

import com.rapido.auth_service.util.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {

        this.userRepository =
                userRepository;

        this.passwordEncoder =
                passwordEncoder;

        this.jwtUtil =
                jwtUtil;
    }

    // REGISTER USER
    public String register(
            RegisterRequest request
    ) {

        User user = new User();

        user.setName(
                request.getName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setPhone(
                request.getPhone()
        );

        user.setRole(
                request.getRole()
        );

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN USER
    public String login(
            LoginRequest request
    ) {

        User user =
                userRepository
                        .findByEmail(
                                request.getEmail()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User Not Found"
                                )
                        );

        // VALIDATE PASSWORD
        if (
                !passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )
        ) {

            throw new RuntimeException(
                    "Invalid Password"
            );
        }

        // GENERATE JWT TOKEN
        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }
}