package com.rapido.admin_service.dto;

import lombok.Data;

@Data
public class AdminLoginRequestDTO {

    private String email;
    private String password;
}