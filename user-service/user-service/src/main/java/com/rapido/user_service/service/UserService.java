package com.rapido.user_service.service;

import com.rapido.user_service.dto.UserProfileDTO;
import com.rapido.user_service.entity.User;
import com.rapido.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileDTO getProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User profile not found"));

        UserProfileDTO dto = new UserProfileDTO();
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setProfileImage(user.getProfileImage());

        return dto;
    }

    public void updateProfile(String email, UserProfileDTO dto) {
        User user = userRepository.findByEmail(email)
                .orElseGet(User::new);

        user.setEmail(email);
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setProfileImage(dto.getProfileImage());
        user.setActive(true);

        userRepository.save(user);
    }
}