package com.digitalsettings.tms.controller;

import com.digitalsettings.tms.model.TemperatureDataCreateRequest;
import com.digitalsettings.tms.service.TemperatureServiceFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/thermostats/{thermostatId}/temperatures")
@RequiredArgsConstructor
@Validated
@Slf4j
public class TemperatureDataController {
    private final TemperatureServiceFacade temperatureServiceFacade;

    @PostMapping
    public ResponseEntity<Void> registerTemperatureData(@PathVariable @Min(1) Long thermostatId,
                                                        @RequestBody @Valid TemperatureDataCreateRequest request) {
        log.info("Registering temperature data for thermostat {} with request {}", thermostatId, request);
        temperatureServiceFacade.registerTemperatureData(thermostatId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
