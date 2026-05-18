package com.rapido.ride_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "rides",
        indexes = {
                @Index(columnList = "userId"),
                @Index(columnList = "driverId"),
                @Index(columnList = "status")
        }
)
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long driverId;

    private Double pickupLatitude;

    private Double pickupLongitude;

    private Double dropLatitude;

    private Double dropLongitude;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private Double estimatedDistance;

    private Double estimatedFare;

    private LocalDateTime requestedAt;

    private LocalDateTime completedAt;
}