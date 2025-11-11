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
        SesionDomainEntity sesionDomainEntity, 
        ActividadEntity actividadEntity
    ) {
        if (sesionDomainEntity == null) {
            return null;
        }

        SesionEntity sesionEntity = new SesionEntity();
        
        if (sesionDomainEntity.getId() != null) {
            sesionEntity.setId(UUID.fromString(sesionDomainEntity.getId()));
        }
        
        sesionEntity.setActividad(actividadEntity);
        sesionEntity.setFechaSesion(sesionDomainEntity.getFechaSesion());
        sesionEntity.setTitulo(sesionDomainEntity.getTitulo());
        
        return sesionEntity;
    }

    public static SesionDomainEntity toDomain(SesionEntity entity) {
        if (entity == null) {
            return null;
        }

        String actividadId = null;
        if (entity.getActividad() != null) {
            actividadId = entity.getActividad().getId().toString();
        }

        return SesionDomainEntity.reconstruct(
            entity.getId().toString(),
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