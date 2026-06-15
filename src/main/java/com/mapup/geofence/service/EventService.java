package com.mapup.geofence.service;

import com.mapup.geofence.dto.EventResponseDto;

import java.util.List;

public interface EventService {

    List<EventResponseDto> getAllEvents();

    List<EventResponseDto> getVehicleEvents(Long vehicleId);

    List<EventResponseDto> getGeofenceEvents(String geofenceId);
}
