package com.mapup.geofence.service;

import com.mapup.geofence.entity.GeofenceEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeofenceEventRepository extends JpaRepository<GeofenceEvent, String> {
}
