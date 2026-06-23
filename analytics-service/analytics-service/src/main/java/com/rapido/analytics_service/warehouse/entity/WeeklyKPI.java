package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "weekly_kpis")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyKPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate weekStart;

    private Long totalRides;

    private Long completedRides;

    private Long cancelledRides;

    private Double totalRevenue;

    private String topCity;

    private LocalDateTime createdAt;
}