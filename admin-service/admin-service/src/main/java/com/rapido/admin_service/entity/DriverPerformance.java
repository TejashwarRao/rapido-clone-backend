package com.rapido.admin_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverPerformance {

    @Id
    private Long driverId;

    private Double averageRating;

    private Integer completedRides;

    private Integer cancelledRides;

    private Double acceptanceRate;

    private Double earnings;

    private Double performanceScore;

    private String region;
}