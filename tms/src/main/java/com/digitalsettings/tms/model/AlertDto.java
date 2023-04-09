package com.digitalsettings.tms.model;

import com.digitalsettings.tms.persistence.entity.AlertEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertDto {
    private Long id;
    private String message;
    private Boolean resolved;
    private Timestamp resolvedAt;
    private Timestamp createdAt;

    public static AlertDto from(AlertEntity entity) {
        return new AlertDto(
                entity.getId(),
                entity.getMessage(),
                entity.getResolved(),
                entity.getResolvedAt(),
                entity.getCreatedAt());
    }
}
