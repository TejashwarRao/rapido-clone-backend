package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fact_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactPayment {

    @Id
    private Long paymentId;

    private Long rideId;

    private Double amount;

    private String paymentStatus;

    private LocalDateTime createdAt;
}