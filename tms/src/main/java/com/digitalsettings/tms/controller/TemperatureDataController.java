package com.digitalsettings.tms.controller;

import com.digitalsettings.tms.model.TemperatureDataCreateRequest;
import com.digitalsettings.tms.service.TemperatureServiceFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thermostats/{thermostatId}/temperatures")
@RequiredArgsConstructor
@Validated
public class TemperatureDataController {
    private final TemperatureServiceFacade temperatureServiceFacade;

    @PostMapping
    public ResponseEntity<Void> registerTemperatureData(@PathVariable @Min(1) Long thermostatId,
                                                        @RequestBody @Valid TemperatureDataCreateRequest request) {
        temperatureServiceFacade.registerTemperatureData(thermostatId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
