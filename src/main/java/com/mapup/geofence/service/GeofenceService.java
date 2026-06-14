package com.mapup.geofence.service;
import com.mapup.geofence.dto.GeofenceRequest;
import com.mapup.geofence.dto.GeofenceResponse;
import com.mapup.geofence.dto.GetGeofencesResponse;

public interface GeofenceService {
    GeofenceResponse createGeofence(
            GeofenceRequest request
    );

    GetGeofencesResponse getGeofences(
            String category
    );

}
