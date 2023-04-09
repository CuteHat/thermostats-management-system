package com.digitalsettings.tms.service;

import com.digitalsettings.tms.model.TemperatureDataCreateRequest;
import com.digitalsettings.tms.persistence.entity.TemperatureDataEntity;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;

public interface TemperatureDataService {
    // TODO security for data feeders
    TemperatureDataEntity create(ThermostatEntity thermostat,
                                 TemperatureDataCreateRequest request);

    TemperatureDataEntity findLatestFor(ThermostatEntity thermostat);
}
