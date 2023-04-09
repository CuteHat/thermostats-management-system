package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.persistence.entity.AlertEntity;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import com.digitalsettings.tms.persistence.repository.AlertRepository;
import com.digitalsettings.tms.persistence.specification.AlertSpecifications;
import com.digitalsettings.tms.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {
    private final AlertRepository repository;

    @Override
    public Page<AlertEntity> getAllFor(ThermostatEntity thermostat, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, AlertEntity.Fields.resolved)); // show unresolved first
        return repository.findAll(AlertSpecifications.byThermostat(thermostat), pageRequest);
    }

    @Override
    public List<AlertEntity> getUnresolvedFor(ThermostatEntity thermostat) {
        return repository.findAll(AlertSpecifications.byThermostat(thermostat)
                .and(AlertSpecifications.unresolved()));
    }

    @Override
    public List<AlertEntity> resolve(List<AlertEntity> alerts, Timestamp timestamp) {
        alerts.forEach(alert -> {
            alert.setResolved(true);
            alert.setResolvedAt(timestamp);
        });
        return repository.saveAll(alerts);
    }

    @Override
    public AlertEntity create(ThermostatEntity thermostat, BigDecimal currentTemperature) {
        return create(thermostat, "Temperature threshold exceeded. Current temperature: " + currentTemperature);
    }

    private AlertEntity create(ThermostatEntity thermostat, String message) {
        return repository.save(AlertEntity.builder()
                .thermostat(thermostat)
                .message(message)
                .resolved(false)
                .build());
    }
}
