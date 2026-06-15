package com.mapup.geofence.controller;

import com.mapup.geofence.dto.EventResponseDto;
import com.mapup.geofence.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor

public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventResponseDto> getAllEvents() {

        return eventService.getAllEvents();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<EventResponseDto> getVehicleEvents(
            @PathVariable Long vehicleId) {

        return eventService.getVehicleEvents(vehicleId);
    }

    @GetMapping("/geofence/{geofenceId}")
    public List<EventResponseDto> getGeofenceEvents(
            @PathVariable String geofenceId) {

        return eventService.getGeofenceEvents(geofenceId);
    }
}
