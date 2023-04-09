package com.digitalsettings.tms.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThermostatCreateRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String location;
    @NotNull
    private BigDecimal configuredTemperature;
    private BigDecimal thresholdTemperature;
}
