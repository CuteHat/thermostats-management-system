package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.model.ThermostatCreateRequest;
import com.digitalsettings.tms.model.ThermostatDto;
import com.digitalsettings.tms.model.ThermostatListItemDto;
import com.digitalsettings.tms.persistence.entity.UserEntity;
import com.digitalsettings.tms.service.ThermostatService;
import com.digitalsettings.tms.service.ThermostatServiceFacade;
import com.digitalsettings.tms.service.UserService;
import com.digitalsettings.tms.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThermostatServiceFacadeImpl implements ThermostatServiceFacade {
    private final ThermostatService thermostatService;
    private final UserService userService;

    @Override
    public List<ThermostatListItemDto> getUserThermostats() {
        return thermostatService
                .getThermostatListItemsByUserId(SecurityUtils.getAuthenticatedUserId())
                .stream()
                .map(ThermostatListItemDto::from)
                .toList();
    }

    @Override
    public ThermostatDto get(Long id) {
        return ThermostatDto.from(thermostatService.get(id));
    }

    @Override
    public ThermostatDto create(ThermostatCreateRequest request) {
        UserEntity owner = userService.findById(SecurityUtils.getAuthenticatedUserId());
        return ThermostatDto.from(thermostatService.create(owner, request));
    }

    @Override
    public ThermostatDto update(Long id, ThermostatCreateRequest request) {
        UserEntity requester = userService.findById(SecurityUtils.getAuthenticatedUserId());
        return ThermostatDto.from(thermostatService.update(id, request, requester));
    }

    @Override
    public void delete(Long id) {
        UserEntity requester = userService.findById(SecurityUtils.getAuthenticatedUserId());
        thermostatService.delete(id, requester);
    }

    @Override
    public List<ThermostatDto> getAll() {
        return thermostatService.getAll()
                .stream()
                .map(ThermostatDto::from)
                .toList();
    }

    @Override
    public List<Long> getAllIds() {
        return thermostatService.getAllIds();
    }
}
