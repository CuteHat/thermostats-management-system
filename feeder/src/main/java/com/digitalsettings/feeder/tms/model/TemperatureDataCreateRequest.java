package com.digitalsettings.feeder.tms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemperatureDataCreateRequest {
    private BigDecimal temperature;
    private Timestamp timestamp;
}
