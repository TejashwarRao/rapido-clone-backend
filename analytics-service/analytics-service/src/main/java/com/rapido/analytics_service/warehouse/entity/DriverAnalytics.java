package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "driver_analytics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long driverId;

    private Long totalRides;

    private Long completedRides;

    private Long cancelledRides;

    private Double acceptanceRate;

    private Double completionRate;

    private Double cancellationRate;

    private Double averageRating;

    private LocalDateTime createdAt;
}