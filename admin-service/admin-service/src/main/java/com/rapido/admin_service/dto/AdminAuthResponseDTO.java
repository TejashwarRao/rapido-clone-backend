package com.rapido.admin_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminAuthResponseDTO {

    private String token;
    private String role;
}