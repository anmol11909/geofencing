package com.mapup.geofence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @Column(nullable = false)
    private String driverName;

    private Double currentLatitude;

    private Double currentLongitude;

    private LocalDateTime createdAt;

    private String currentGeofenceId;




    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}