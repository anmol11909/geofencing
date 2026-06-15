package com.mapup.geofence.entity;

import com.mapup.geofence.enums.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "geofence_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeofenceEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "geofence_id")
    private Geofence geofence;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private LocalDateTime eventTime;

    @PrePersist
    public void prePersist() {
        eventTime = LocalDateTime.now();
    }
}