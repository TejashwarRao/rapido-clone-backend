package com.rapido.admin_service.dto;

import lombok.Data;

@Data
public class FraudSimulationRequestDTO {

    private Long userId;
    private String fraudType;
    private String description;
    private String severity;
}