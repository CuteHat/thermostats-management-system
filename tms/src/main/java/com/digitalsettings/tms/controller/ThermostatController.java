package com.digitalsettings.tms.controller;

import com.digitalsettings.tms.model.ThermostatDto;
import com.digitalsettings.tms.service.ThermostatServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thermostats")
@RequiredArgsConstructor
public class ThermostatController {
    private final ThermostatServiceFacade thermostatServiceFacade;

    @GetMapping
    public List<ThermostatDto> getThermostats() {
        return thermostatServiceFacade.getAll();
    }

    @GetMapping("/id")
    public List<Long> getThermostatIds() {
        return thermostatServiceFacade.getAllIds();
    }
}
