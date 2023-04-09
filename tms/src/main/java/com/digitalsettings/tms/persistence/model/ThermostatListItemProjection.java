package com.digitalsettings.tms.persistence.model;

import java.math.BigDecimal;

public class ThermostatListItemProjection {
    private Long id;
    private String name;
    private String location;
    private BigDecimal thresholdTemperature;
    private BigDecimal configuredTemperature;
    private Boolean isCritical;

    public ThermostatListItemProjection(Long id,
                                        String name,
                                        String location,
                                        BigDecimal thresholdTemperature,
                                        BigDecimal configuredTemperature,
                                        Boolean isCritical) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.thresholdTemperature = thresholdTemperature;
        this.configuredTemperature = configuredTemperature;
        this.isCritical = isCritical;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public BigDecimal getThresholdTemperature() {
        return thresholdTemperature;
    }

    public BigDecimal getConfiguredTemperature() {
        return configuredTemperature;
    }

    public Boolean getCritical() {
        return isCritical;
    }
}
