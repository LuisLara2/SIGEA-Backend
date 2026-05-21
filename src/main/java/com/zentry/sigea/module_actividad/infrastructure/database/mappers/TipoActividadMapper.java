package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import java.util.UUID;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.TipoActividadEntity;

/**
 * Mapper para convertir entre TipoActividad (dominio) y TipoActividadEntity (JPA)
 */

public class TipoActividadMapper {
    
    /**
     * Valida si un string es un UUID válido
     */
    private static boolean isValidUUID(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    public static TipoActividadDomainEntity toDomain(TipoActividadEntity tipoActividadEntity) {
        if (tipoActividadEntity == null) {
            return null;
        }
        
        TipoActividadDomainEntity tipoActividadDomainEntity = new TipoActividadDomainEntity();
        
        tipoActividadDomainEntity.setTipoActividadId(tipoActividadEntity.getId().toString());
        tipoActividadDomainEntity.setNombreActividad(tipoActividadEntity.getNombreActividad());
        tipoActividadDomainEntity.setDescripcion(tipoActividadEntity.getDescripcion());
        tipoActividadDomainEntity.setCreatedAt(tipoActividadEntity.getCreatedAt());
        tipoActividadDomainEntity.setUpdatedAt(tipoActividadEntity.getUpdatedAt());

        return tipoActividadDomainEntity;
    }
    
    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    public static TipoActividadEntity toEntity(TipoActividadDomainEntity tipoActividadDomainEntity) {
        if (tipoActividadDomainEntity == null) {
            return null;
        }
        
        TipoActividadEntity tipoActividadEntity = new TipoActividadEntity();
        
        String tipoId = tipoActividadDomainEntity.getTipoActividadId();
        if (tipoId != null && !tipoId.isBlank()) {
            if (!isValidUUID(tipoId)) {
                throw new IllegalArgumentException("El tipoActividadId no es un UUID válido: " + tipoId);
            }
            tipoActividadEntity.setId(UUID.fromString(tipoId));
        }
        tipoActividadEntity.setNombreActividad(tipoActividadDomainEntity.getNombreActividad());
        tipoActividadEntity.setDescripcion(tipoActividadDomainEntity.getDescripcion());
        tipoActividadEntity.setCreatedAt(tipoActividadDomainEntity.getCreatedAt());
        tipoActividadEntity.setUpdatedAt(tipoActividadDomainEntity.getUpdatedAt());
        
        return tipoActividadEntity;
    }
}