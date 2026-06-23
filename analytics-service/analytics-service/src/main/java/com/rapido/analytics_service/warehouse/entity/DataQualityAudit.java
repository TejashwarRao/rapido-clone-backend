package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "data_quality_audit")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataQualityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    private String errorMessage;

    private LocalDateTime createdAt;
}