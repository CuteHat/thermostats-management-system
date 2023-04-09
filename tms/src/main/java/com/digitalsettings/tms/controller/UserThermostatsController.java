package com.digitalsettings.tms.controller;

import com.digitalsettings.tms.model.ThermostatCreateRequest;
import com.digitalsettings.tms.model.ThermostatDto;
import com.digitalsettings.tms.model.ThermostatListItemDto;
import com.digitalsettings.tms.service.ThermostatServiceFacade;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/thermostats")
@RequiredArgsConstructor
@Validated
public class UserThermostatsController {
    private final ThermostatServiceFacade thermostatServiceFacade;

    @GetMapping
    public ResponseEntity<List<ThermostatListItemDto>> getUserThermostats() {
        List<ThermostatListItemDto> thermostats = thermostatServiceFacade.getUserThermostats();
        return ResponseEntity.ok(thermostats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThermostatDto> getThermostat(@PathVariable @Min(1) Long id) {
        ThermostatDto thermostat = thermostatServiceFacade.get(id);
        return ResponseEntity.ok(thermostat);
    }

    @PostMapping
    public ResponseEntity<ThermostatDto> createThermostat(@RequestBody ThermostatCreateRequest request) {
        ThermostatDto createdThermostat = thermostatServiceFacade.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdThermostat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThermostat(@PathVariable Long id) {
        thermostatServiceFacade.delete(id);
        return ResponseEntity.noContent().build();
    }
}
