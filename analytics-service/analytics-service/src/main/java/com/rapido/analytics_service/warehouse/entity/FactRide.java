package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fact_rides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactRide {

    @Id
    private Long rideId;

    private Long userId;

    private Long driverId;

    private Double fare;

    private Double distance;

    private String status;

    private String city;

    private LocalDateTime createdAt;
    private Double pickupLat;

    private Double pickupLon;

    private Double dropLat;

    private Double dropLon;
}