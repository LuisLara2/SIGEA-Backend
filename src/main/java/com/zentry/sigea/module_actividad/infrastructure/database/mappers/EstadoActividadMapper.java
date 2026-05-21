package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import java.util.UUID;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.EstadoActividadEntity;

/**
 * Mapper para convertir entre EstadoActividad (dominio) y EstadoActividadEntity (JPA)
 */
public class EstadoActividadMapper {
    
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
     * Convierte de entidad de dominio a entidad JPA
     */
    public static EstadoActividadEntity toEntity(EstadoActividadDomainEntity estadoActividadDomainEntity) {
        if (estadoActividadDomainEntity == null) {
            return null;
        }
        
        EstadoActividadEntity estadoActividadEntity = new EstadoActividadEntity();
        
        String estadoId = estadoActividadDomainEntity.getEstadoActividadId();
        if (estadoId != null && !estadoId.isBlank()) {
            if (!isValidUUID(estadoId)) {
                throw new IllegalArgumentException("El estadoActividadId no es un UUID válido: " + estadoId);
            }
            estadoActividadEntity.setId(UUID.fromString(estadoId));
        }
        estadoActividadEntity.setCodigo(estadoActividadDomainEntity.getCodigo());
        estadoActividadEntity.setEtiqueta(estadoActividadDomainEntity.getEtiqueta());
        
        return estadoActividadEntity;
    }

    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    public static EstadoActividadDomainEntity toDomain(EstadoActividadEntity estadoActividadEntity) {
        if (estadoActividadEntity == null) {
            return null;
        }

        EstadoActividadDomainEntity estadoActividadDomainEntity = new EstadoActividadDomainEntity();
        
        estadoActividadDomainEntity.setEstadoActividadId(estadoActividadEntity.getId().toString());
        estadoActividadDomainEntity.setCodigo(estadoActividadEntity.getCodigo());
        estadoActividadDomainEntity.setEtiqueta(estadoActividadEntity.getEtiqueta());
        
        return estadoActividadDomainEntity;
    }
}