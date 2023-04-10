package com.digitalsettings.tms.controller;

import com.digitalsettings.tms.model.ThermostatCreateRequest;
import com.digitalsettings.tms.model.ThermostatDto;
import com.digitalsettings.tms.model.ThermostatListItemDto;
import com.digitalsettings.tms.service.ThermostatServiceFacade;
import jakarta.validation.Valid;
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
public class UserThermostatController {
    private final ThermostatServiceFacade thermostatServiceFacade;

    @GetMapping
    public ResponseEntity<List<ThermostatListItemDto>> getUserThermostats() {
        List<ThermostatListItemDto> thermostats = thermostatServiceFacade.getUserThermostats();
        return ResponseEntity.ok(thermostats);
    }

    @PostMapping
    public ResponseEntity<ThermostatDto> createThermostat(@Valid @RequestBody ThermostatCreateRequest request) {
        ThermostatDto createdThermostat = thermostatServiceFacade.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdThermostat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThermostatDto> createThermostat(@PathVariable @Min(1) Long id, @Valid @RequestBody ThermostatCreateRequest request) {
        ThermostatDto updatedThermostat = thermostatServiceFacade.update(id, request);
        return ResponseEntity.ok(updatedThermostat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThermostat(@PathVariable Long id) {
        thermostatServiceFacade.delete(id);
        return ResponseEntity.noContent().build();
    }
}
