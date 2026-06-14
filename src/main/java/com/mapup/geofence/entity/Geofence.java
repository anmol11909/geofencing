package com.mapup.geofence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "geofences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Geofence {

    @Id
    private String id;

    private String name;

    private String description;

    private String category;

    @Lob
    private String coordinates;

    private LocalDateTime createdAt;
}
