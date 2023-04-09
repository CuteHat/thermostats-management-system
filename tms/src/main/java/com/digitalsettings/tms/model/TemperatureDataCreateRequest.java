package com.digitalsettings.tms.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemperatureDataCreateRequest {
    @NotNull
    private BigDecimal temperature;
    @NotNull
    private Timestamp timestamp;
}
