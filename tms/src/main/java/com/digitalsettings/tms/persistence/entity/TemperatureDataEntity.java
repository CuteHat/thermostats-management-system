package com.digitalsettings.tms.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TEMPERATURE_DATA")
@FieldNameConstants
@Builder
public class TemperatureDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ThermostatEntity thermostat;

    @Column(nullable = false)
    private BigDecimal temperature;

    @Column(nullable = false)
    private Timestamp timestamp;

    @CreationTimestamp
    private Timestamp createdAt;
}
