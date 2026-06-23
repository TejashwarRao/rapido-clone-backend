package com.rapido.analytics_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long totalRides;

    private Long completedRides;

    private Long cancelledRides;

    private Double totalRevenue;
}