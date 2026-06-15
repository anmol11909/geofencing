package com.mapup.geofence.dto;

import com.mapup.geofence.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EventResponseDto {
    private Long id;

    private Long vehicleId;

    private String geofenceId;

    private EventType eventType;

    private LocalDateTime eventTime;
}
