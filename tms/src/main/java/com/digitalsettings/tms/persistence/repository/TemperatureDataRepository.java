package com.digitalsettings.tms.persistence.repository;

import com.digitalsettings.tms.persistence.entity.TemperatureDataEntity;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureDataRepository extends JpaRepository<TemperatureDataEntity, Long> {
    TemperatureDataEntity findFirstByThermostatOrderByTimestampDesc(ThermostatEntity thermostat);
}
