package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.EstadoActividadEntity;

/**
 * Mapper para convertir entre EstadoActividad (dominio) y EstadoActividadEntity (JPA)
 */
public class EstadoActividadMapper {
    
    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    public static EstadoActividadEntity toEntity(EstadoActividadDomainEntity estadoActividadDomainEntity) {
        if (estadoActividadDomainEntity == null) {
            return null;
        }
        
        EstadoActividadEntity estadoActividadEntity = new EstadoActividadEntity();

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

        estadoActividadDomainEntity.setCodigo(estadoActividadEntity.getCodigo());
        estadoActividadDomainEntity.setEtiqueta(estadoActividadEntity.getCodigo());
        
        return estadoActividadDomainEntity;
    }
}