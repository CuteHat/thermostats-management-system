package com.digitalsettings.tms.persistence.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "THERMOSTAT_ALERTS")
@FieldNameConstants
@Builder
public class AlertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ThermostatEntity thermostat;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Boolean resolved;

    private Timestamp resolvedAt;

    @CreationTimestamp
    private Timestamp createdAt;

    @PostConstruct
    public void init() {
        resolved = false;
    }
}