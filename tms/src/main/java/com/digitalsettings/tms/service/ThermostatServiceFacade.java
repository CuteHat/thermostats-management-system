package com.digitalsettings.tms.service;

import com.digitalsettings.tms.model.ThermostatCreateRequest;
import com.digitalsettings.tms.model.ThermostatDto;
import com.digitalsettings.tms.model.ThermostatListItemDto;

import java.util.List;

public interface ThermostatServiceFacade {
    List<ThermostatListItemDto> getUserThermostats();

    ThermostatDto get(Long id);

    ThermostatDto create(ThermostatCreateRequest request);

    void delete(Long id);
}
