package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "fact_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactNotification {

    @Id
    private Long notificationId;

    private Long userId;

    private String notificationType;

    private LocalDateTime sentAt;
}