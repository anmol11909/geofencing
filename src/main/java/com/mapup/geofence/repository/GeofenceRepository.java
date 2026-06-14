package com.mapup.geofence.repository;
import com.mapup.geofence.entity.Geofence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeofenceRepository extends JpaRepository<Geofence, String> {
}
