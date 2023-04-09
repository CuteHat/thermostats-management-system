package com.digitalsettings.tms.persistence.specification;

import com.digitalsettings.tms.persistence.entity.AlertEntity;
import com.digitalsettings.tms.persistence.entity.ThermostatEntity;
import org.springframework.data.jpa.domain.Specification;

public class AlertSpecifications {

    public static Specification<AlertEntity> byThermostat(ThermostatEntity thermostat) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(AlertEntity.Fields.thermostat), thermostat);
    }

    public static Specification<AlertEntity> unresolved() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get(AlertEntity.Fields.resolved));
    }
}
