package com.zentry.sigea.module_sesiones.infrastructure.database.mappers;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity;

import java.util.UUID;  

/**
 * Mapper entre entidad de dominio y entidad JPA
 * Convierte entre SesionDomainEntity ↔ SesionEntity
 */
public class SesionMapper {

    public static SesionEntity toEntity(
        SesionDomainEntity domain, 
        ActividadEntity actividadEntity
    ) {
        if (domain == null) {
            return null;
        }

        SesionEntity entity = new SesionEntity();
        
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        
        entity.setActividad(actividadEntity);
        entity.setFechaSesion(domain.getFechaSesion());
        entity.setTitulo(domain.getTitulo());
        
        return entity;
    }

    public static SesionDomainEntity toDomain(SesionEntity entity) {
        if (entity == null) {
            return null;
        }

        UUID actividadId = null;
        if (entity.getActividad() != null) {
            actividadId = entity.getActividad().getId();
        }

        return SesionDomainEntity.reconstruct(
            entity.getId(),
            actividadId,
            entity.getFechaSesion(),
            entity.getTitulo(),
            null, 
            null  
        );
    }


    public static void updateEntity(
        SesionEntity entity, 
        SesionDomainEntity domain,
        ActividadEntity actividadEntity
    ) {
        if (entity == null || domain == null) {
            return;
        }

        entity.setActividad(actividadEntity);
        entity.setFechaSesion(domain.getFechaSesion());
        entity.setTitulo(domain.getTitulo());
    }
}