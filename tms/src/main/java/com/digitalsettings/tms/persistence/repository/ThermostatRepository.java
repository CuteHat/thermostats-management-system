package com.digitalsettings.tms.persistence.repository;

import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import com.digitalsettings.tms.persistence.model.ThermostatListItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThermostatRepository extends JpaRepository<ThermostatEntity, Long> {
    @Query("SELECT new com.digitalsettings.tms.persistence.model.ThermostatListItemProjection(" +
            "t.id, t.name, t.location, t.thresholdTemperature, t.configuredTemperature, t.isCritical) " +
            "FROM ThermostatEntity t " +
            "WHERE t.user.id = :userId")
    List<ThermostatListItemProjection> findThermostatListItemsByUserId(Long userId);

    @Query("SELECT t.id FROM ThermostatEntity t")
    List<Long> findAllIds();
}
