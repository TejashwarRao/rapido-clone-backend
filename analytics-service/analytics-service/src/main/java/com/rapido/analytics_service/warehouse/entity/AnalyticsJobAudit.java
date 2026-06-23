package com.rapido.analytics_service.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analytics_job_audit")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsJobAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Long recordsProcessed;

    private Long failures;
}