package com.mapup.geofence.repository;
import com.mapup.geofence.entity.Geofence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeofenceRepository extends JpaRepository<Geofence, String> {
    List<Geofence> findByCategory(String category);
}
