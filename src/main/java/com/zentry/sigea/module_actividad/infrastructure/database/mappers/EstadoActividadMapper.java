package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.EstadoActividadEntity;

/**
 * Mapper para convertir entre EstadoActividad (dominio) y EstadoActividadEntity (JPA)
 */
public class EstadoActividadMapper {
    
    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    public static EstadoActividad toDomain(EstadoActividadEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EstadoActividad(
            entity.getId(),
            entity.getCodigo(),
            entity.getEtiqueta()
        );
    }
    
    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    public static EstadoActividadEntity toEntity(EstadoActividad domain) {
        if (domain == null) {
            return null;
        }
        
        EstadoActividadEntity entity = new EstadoActividadEntity();
        entity.setId(domain.getId());
        entity.setCodigo(domain.getCodigo());
        entity.setEtiqueta(domain.getEtiqueta());
        return entity;
    }
}