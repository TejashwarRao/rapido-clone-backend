package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_kpis")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyKPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;

    private Long totalRides;

    private Long completedRides;

    private Long cancelledRides;

    private Double totalRevenue;

    private LocalDateTime createdAt;
}