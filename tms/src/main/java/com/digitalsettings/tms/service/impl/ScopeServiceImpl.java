package com.digitalsettings.tms.service.impl;

import com.digitalsettings.tms.persistence.entity.ScopeEntity;
import com.digitalsettings.tms.persistence.model.SCOPE;
import com.digitalsettings.tms.persistence.repository.ScopeRepository;
import com.digitalsettings.tms.service.ScopeService;
import com.digitalsettings.tms.util.HandledExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScopeServiceImpl implements ScopeService {
    private final ScopeRepository repository;

    @Override
    public ScopeEntity getByName(SCOPE name) {
        return repository.findByName(name)
                .orElseThrow(() -> HandledExceptionFactory.getNotFoundException("scope not found"));
    }

    @Override
    public ScopeEntity getByName(String name) {
        return getByName(SCOPE.valueOf(name));
    }

}