package com.digitalsettings.tms.service;

import com.digitalsettings.tms.model.ThermostatCreateRequest;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import com.digitalsettings.tms.persistence.entity.UserEntity;
import com.digitalsettings.tms.persistence.model.ThermostatListItemProjection;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ThermostatService {
    ThermostatEntity create(UserEntity owner, ThermostatCreateRequest request);

    ThermostatEntity update(Long id,
                            ThermostatCreateRequest request,
                            UserEntity requester);

    void delete(Long id, UserEntity requester);

    ThermostatEntity get(Long id);

    ThermostatEntity markAsCritical(ThermostatEntity thermostat);

    ThermostatEntity markAsNotCritical(ThermostatEntity thermostat);

    boolean thresholdIsExceeded(ThermostatEntity thermostat, BigDecimal temperature);

    List<ThermostatListItemProjection> getThermostatListItemsByUserId(Long userId);

    List<ThermostatEntity> getAll();

    List<Long> getAllIds();
}
