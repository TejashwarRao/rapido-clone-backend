package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "monthly_kpis")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyKPI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String monthName;

    private Long totalRides;

    private Long completedRides;

    private Long cancelledRides;

    private Double totalRevenue;

    private String topCity;

    private LocalDateTime createdAt;
}