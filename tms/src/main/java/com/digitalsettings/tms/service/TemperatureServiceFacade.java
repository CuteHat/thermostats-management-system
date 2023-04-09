package com.digitalsettings.tms.service;

import com.digitalsettings.tms.model.TemperatureDataCreateRequest;
import jakarta.transaction.Transactional;

public interface TemperatureServiceFacade {
    @Transactional
    void registerTemperatureData(Long thermostatId, TemperatureDataCreateRequest request);
}
