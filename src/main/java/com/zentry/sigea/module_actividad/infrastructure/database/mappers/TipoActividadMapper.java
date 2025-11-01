package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import com.zentry.sigea.infrastructure.database.entities.TipoActividadEntity;
import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;

/**
 * Mapper para convertir entre TipoActividad (dominio) y TipoActividadEntity (JPA)
 */

public class TipoActividadMapper {
    
    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    public static TipoActividad toDomain(TipoActividadEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new TipoActividad(
            entity.getId(),
            entity.getNombreActividad(),
            entity.getDescripcion(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
    
    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    public static TipoActividadEntity toEntity(TipoActividad domain) {
        if (domain == null) {
            return null;
        }
        
        TipoActividadEntity entity = new TipoActividadEntity();
        entity.setId(domain.getId());
        entity.setNombreActividad(domain.getNombreActividad());
        entity.setDescripcion(domain.getDescripcion());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }
}