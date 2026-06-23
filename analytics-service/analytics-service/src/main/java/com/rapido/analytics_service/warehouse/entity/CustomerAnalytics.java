package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;

    private Long newCustomers;

    private Long returningCustomers;

    private Double retentionRate;

    private Double churnRate;

    private LocalDateTime createdAt;
}