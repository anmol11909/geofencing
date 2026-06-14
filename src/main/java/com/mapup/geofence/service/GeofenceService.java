package com.mapup.geofence.service;
import com.mapup.geofence.dto.GeofenceRequest;
import com.mapup.geofence.dto.GeofenceResponse;

public interface GeofenceService {
    GeofenceResponse createGeofence(
            GeofenceRequest request
    );
}
