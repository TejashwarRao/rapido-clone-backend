package com.rapido.admin_service.dto;

import com.rapido.admin_service.entity.AdminRole;
import lombok.Data;

@Data
public class AdminRegisterRequestDTO {

    private String name;
    private String email;
    private String password;
    private AdminRole role;
}