package com.digitalsettings.tms.persistence.repository;

import com.digitalsettings.tms.persistence.entity.ScopeEntity;
import com.digitalsettings.tms.persistence.model.SCOPE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScopeRepository extends CrudRepository<ScopeEntity, Long> {
    Optional<ScopeEntity> findByName(SCOPE SCOPE);
}