package com.mapup.geofence.repository;

import com.mapup.geofence.entity.GeofenceEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeofenceEventRepository extends JpaRepository<GeofenceEvent, Long> {

    List<GeofenceEvent> findByVehicle_Id(Long vehicleId);

    List<GeofenceEvent> findByGeofence_Id(String geofenceId);
}
