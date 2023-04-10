package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.model.TemperatureDataCreateRequest;
import com.digitalsettings.tms.persistence.entity.AlertEntity;
import com.digitalsettings.tms.persistence.entity.TemperatureDataEntity;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import com.digitalsettings.tms.service.AlertService;
import com.digitalsettings.tms.service.TemperatureDataService;
import com.digitalsettings.tms.service.TemperatureServiceFacade;
import com.digitalsettings.tms.service.ThermostatService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemperatureServiceFacadeImpl implements TemperatureServiceFacade {
    private final ThermostatService thermostatService;
    private final TemperatureDataService temperatureDataService;
    private final AlertService alertService;

    /**
     * Registers the temperature data for a specified thermostat and updates its state (e.g., critical status and alerts).
     *
     * @param thermostatId The ID of the thermostat for which the temperature data is being registered.
     * @param request      The temperature data request containing the temperature value and timestamp.
     */
    @Override
    @Transactional
    public void registerTemperatureData(Long thermostatId, TemperatureDataCreateRequest request) {
        ThermostatEntity thermostat = thermostatService.get(thermostatId);

        // register temperature data
        TemperatureDataEntity newData = temperatureDataService.create(thermostat, request);

        // get latest temperature data, current temperature data might be outdated
        TemperatureDataEntity temperatureData = temperatureDataService.findLatestFor(thermostat);

        // historical data, skipping rest of the logic
        if (!newData.getId().equals(temperatureData.getId()))
            return;

        // mark as critical if real temperature is above configured temperature
        if (thermostat.getConfiguredTemperature().compareTo(temperatureData.getTemperature()) < 0)
            thermostatService.markAsCritical(thermostat);
        else if (thermostat.getIsCritical())
            thermostatService.markAsNotCritical(thermostat);


        // register alert if temperature is above threshold
        if (thermostatService.thresholdIsExceeded(thermostat, temperatureData.getTemperature())) {
            alertService.create(thermostat, request.getTemperature());
            return;
        }

        // if temperature is below threshold, resolve unresolved alerts
        List<AlertEntity> unresolvedAlerts = alertService.getUnresolvedFor(thermostat);
        if (!unresolvedAlerts.isEmpty())
            alertService.resolve(unresolvedAlerts, temperatureData.getTimestamp());
    }

}
