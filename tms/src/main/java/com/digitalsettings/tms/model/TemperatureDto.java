package com.digitalsettings.tms.model;

import com.digitalsettings.tms.persistence.entity.TemperatureDataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemperatureDto {
    private Long id;
    private BigDecimal temperature;
    private Timestamp timestamp;

    public static TemperatureDto from(TemperatureDataEntity entity) {
        return new TemperatureDto(
                entity.getId(),
                entity.getTemperature(),
                entity.getTimestamp());
    }
}