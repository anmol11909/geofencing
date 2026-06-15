package com.mapup.geofence.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VehicleResponseDto {
    private Long id;

    private String vehicleNumber;

    private String driverName;

    private Double currentLatitude;

    private Double currentLongitude;

    private LocalDateTime createdAt;
}
