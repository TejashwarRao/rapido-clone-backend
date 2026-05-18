package com.rapido.user_service.controller;

import com.rapido.user_service.dto.UserProfileDTO;
import com.rapido.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET USER PROFILE
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(
            @RequestParam String email
    ) {

        return ResponseEntity.ok(
                userService.getProfile(email)
        );
    }

    // UPDATE USER PROFILE
    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(
            @RequestParam String email,
            @Valid @RequestBody UserProfileDTO dto
    ) {

        userService.updateProfile(email, dto);

        return ResponseEntity.ok(
                "Profile Updated Successfully"
        );
    }
}