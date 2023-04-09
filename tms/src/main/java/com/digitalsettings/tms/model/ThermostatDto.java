package com.digitalsettings.tms.model;

import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThermostatDto {
    private Long id;
    private String name;
    private String location;
    private BigDecimal thresholdTemperature;
    private BigDecimal configuredTemperature;
    private Boolean isCritical;
    private List<TemperatureDto> temperatures;
    private List<AlertDto> alerts;

    public static ThermostatDto from(ThermostatEntity entity) {
        return new ThermostatDto(
                entity.getId(),
                entity.getName(),
                entity.getLocation(),
                entity.getThresholdTemperature(),
                entity.getConfiguredTemperature(),
                entity.getIsCritical(),
                getTemperatureDtos(entity),
                getAlertDtos(entity));
    }

    private static List<TemperatureDto> getTemperatureDtos(ThermostatEntity entity) {
        if (entity.getTemperatures() == null) {
            return List.of();
        }
        // sort by timestamp descending
        return entity.getTemperatures().stream()
                .map(TemperatureDto::from)
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .toList();
    }

    private static List<AlertDto> getAlertDtos(ThermostatEntity entity) {
        if (entity.getAlerts() == null) {
            return List.of();
        }
        // unresolved alerts first
        return entity.getAlerts().stream()
                .map(AlertDto::from)
                .sorted(Comparator.comparing(AlertDto::getResolved))
                .toList();
    }
}
