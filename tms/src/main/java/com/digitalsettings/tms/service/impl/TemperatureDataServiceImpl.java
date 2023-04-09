package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.model.TemperatureDataCreateRequest;
import com.digitalsettings.tms.persistence.entity.TemperatureDataEntity;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import com.digitalsettings.tms.persistence.repository.TemperatureDataRepository;
import com.digitalsettings.tms.service.TemperatureDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureDataServiceImpl implements TemperatureDataService {
    private final TemperatureDataRepository repository;

    @Override
    public TemperatureDataEntity create(ThermostatEntity thermostat,
                                        TemperatureDataCreateRequest request) {
        return repository.save(TemperatureDataEntity.builder()
                .thermostat(thermostat)
                .temperature(request.getTemperature())
                .timestamp(request.getTimestamp())
                .build());
    }

    @Override
    public TemperatureDataEntity findLatestFor(ThermostatEntity thermostat) {
        return repository.findFirstByThermostatOrderByTimestampDesc(thermostat);
    }
}
