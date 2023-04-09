package com.digitalsettings.tms.service;

import com.digitalsettings.tms.persistence.entity.ScopeEntity;
import com.digitalsettings.tms.persistence.model.SCOPE;

public interface ScopeService {
    ScopeEntity getByName(SCOPE name);

    ScopeEntity getByName(String name);
}