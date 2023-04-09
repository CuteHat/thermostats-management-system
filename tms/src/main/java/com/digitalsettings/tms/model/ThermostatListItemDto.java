package com.digitalsettings.tms.model;

import com.digitalsettings.tms.persistence.model.ThermostatListItemProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermostatListItemDto {
    private Long id;
    private String name;
    private String location;
    private BigDecimal thresholdTemperature;
    private BigDecimal configuredTemperature;
    private Boolean isCritical;

    public static ThermostatListItemDto from(ThermostatListItemProjection projection) {
        return new ThermostatListItemDto(
                projection.getId(),
                projection.getName(),
                projection.getLocation(),
                projection.getThresholdTemperature(),
                projection.getConfiguredTemperature(),
                projection.getCritical());
    }
}
