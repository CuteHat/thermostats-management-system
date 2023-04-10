package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.model.ThermostatCreateRequest;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import com.digitalsettings.tms.persistence.entity.UserEntity;
import com.digitalsettings.tms.persistence.model.ThermostatListItemProjection;
import com.digitalsettings.tms.persistence.repository.ThermostatRepository;
import com.digitalsettings.tms.service.ThermostatService;
import com.digitalsettings.tms.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThermostatServiceImpl implements ThermostatService {
    private final ThermostatRepository repository;

    @Override
    public ThermostatEntity create(UserEntity owner, ThermostatCreateRequest request) {
        return repository.save(ThermostatEntity.builder()
                .user(owner)
                .isCritical(false)
                .name(request.getName())
                .location(request.getLocation())
                .configuredTemperature(request.getConfiguredTemperature())
                .thresholdTemperature(request.getThresholdTemperature())
                .build());
    }

    @Override
    public ThermostatEntity update(Long id,
                                   ThermostatCreateRequest request,
                                   UserEntity requester) {
        ThermostatEntity thermostat = get(id);
        if (!thermostat.getUser().equals(requester))
            throw HandledExceptionFactory.getForbiddenException("You are not allowed to update this thermostat");

        thermostat.setName(request.getName());
        thermostat.setLocation(request.getLocation());
        thermostat.setThresholdTemperature(request.getThresholdTemperature());
        return repository.save(thermostat);
    }

    @Override
    public void delete(Long id, UserEntity requester) {
        ThermostatEntity thermostat = get(id);
        if (!thermostat.getUser().equals(requester))
            throw HandledExceptionFactory.getForbiddenException("You are not allowed to delete this thermostat");

        repository.delete(thermostat);
    }

    @Override
    public ThermostatEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> HandledExceptionFactory.getNotFoundException("Thermostat not found"));
    }

    @Override
    public ThermostatEntity markAsCritical(ThermostatEntity thermostat) {
        thermostat.setIsCritical(true);
        return repository.save(thermostat);
    }

    @Override
    public ThermostatEntity markAsNotCritical(ThermostatEntity thermostat) {
        thermostat.setIsCritical(false);
        return repository.save(thermostat);
    }

    @Override
    public boolean thresholdIsExceeded(ThermostatEntity thermostat, BigDecimal temperature) {
        if (thermostat.getThresholdTemperature() == null)
            return false;
        return thermostat.getThresholdTemperature().compareTo(temperature) < 0;
    }

    @Override
    public List<ThermostatListItemProjection> getThermostatListItemsByUserId(Long userId) {
        return repository.findThermostatListItemsByUserId(userId);
    }

    @Override
    public List<ThermostatEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Long> getAllIds() {
        return repository.findAllIds();
    }

}
