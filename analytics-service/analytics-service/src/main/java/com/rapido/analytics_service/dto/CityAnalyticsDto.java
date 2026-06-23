package com.rapido.analytics_service.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityAnalyticsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;

    private Long totalRides;

    private Double totalRevenue;
}