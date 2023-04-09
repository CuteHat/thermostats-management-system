package com.digitalsettings.tms.service;

import com.digitalsettings.tms.persistence.entity.AlertEntity;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface AlertService {
    Page<AlertEntity> getAllFor(ThermostatEntity thermostat, int page, int size);

    List<AlertEntity> getUnresolvedFor(ThermostatEntity thermostat);

    List<AlertEntity> resolve(List<AlertEntity> alerts, Timestamp timestamp);

    AlertEntity create(ThermostatEntity thermostat, BigDecimal currentTemperature);
}
