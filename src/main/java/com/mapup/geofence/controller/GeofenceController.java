package com.mapup.geofence.controller;
import com.mapup.geofence.dto.GeofenceRequest;
import com.mapup.geofence.dto.GeofenceResponse;
import com.mapup.geofence.service.GeofenceService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/geofences")
@RequiredArgsConstructor
public class GeofenceController {

    private final GeofenceService geofenceService;

    @PostMapping
    public ResponseEntity<GeofenceResponse>
    createGeofence(
            @Valid
            @RequestBody
            GeofenceRequest request) {

        return ResponseEntity.ok(
                geofenceService.createGeofence(request)
        );
    }
}
