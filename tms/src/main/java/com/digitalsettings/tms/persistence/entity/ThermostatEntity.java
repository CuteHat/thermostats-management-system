package com.digitalsettings.tms.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "THERMOSTATS")
@FieldNameConstants
@Builder
public class ThermostatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private BigDecimal thresholdTemperature;

    @Column(nullable = false)
    private BigDecimal configuredTemperature;

    @Column(nullable = false)
    private Boolean isCritical;

    @OneToMany(mappedBy = "thermostat")
    private List<TemperatureDataEntity> temperatures;

    @OneToMany(mappedBy = "thermostat")
    private List<AlertEntity> alerts;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}