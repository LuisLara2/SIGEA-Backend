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

    public static SesionEntity toEntity(SesionDomainEntity domain, ActividadEntity actividad) {
        SesionEntity entity = new SesionEntity();
        entity.setId(domain.getId() != null ? UUID.fromString(domain.getId()) : null);
        entity.setActividad(actividad);
        entity.setTitulo(domain.getTitulo());
        entity.setDescripcion(domain.getDescripcion());
        entity.setFechaSesion(domain.getFechaSesion());
        entity.setHoraInicio(domain.getHoraInicio());
        entity.setHoraFin(domain.getHoraFin());
        entity.setPonente(domain.getPonente());
        entity.setModalidad(domain.getModalidad()); 
        entity.setLugarSesion(domain.getLugarSesion() != null ? domain.getLugarSesion() : "");
        entity.setLinkVirtual(domain.getLinkVirtual() != null ? domain.getLinkVirtual() : "");
        entity.setOrden(domain.getOrden());
        return entity;
    }

    public static SesionDomainEntity toDomain(SesionEntity entity) {
        return SesionDomainEntity.reconstruct(
            entity.getId() != null ? entity.getId().toString() : null,
            entity.getActividad() != null ? entity.getActividad().getId().toString() : null,
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getFechaSesion(),
            entity.getHoraInicio(),
            entity.getHoraFin(),
            entity.getPonente(),
            entity.getModalidad(),
            entity.getLugarSesion(),
            entity.getLinkVirtual(),
            entity.getOrden(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public static void updateEntity(
        SesionEntity entity, 
        SesionDomainEntity domain,
        ActividadEntity actividadEntity
    ) {
        if (entity == null || domain == null) {
            throw new IllegalArgumentException("La entidad o el dominio no pueden ser nulos");
        }

        entity.setActividad(actividadEntity);
        entity.setTitulo(domain.getTitulo());
        entity.setDescripcion(domain.getDescripcion());
        entity.setFechaSesion(domain.getFechaSesion());
        entity.setHoraInicio(domain.getHoraInicio());
        entity.setHoraFin(domain.getHoraFin());
        entity.setPonente(domain.getPonente());
        entity.setModalidad(domain.getModalidad());
        entity.setLugarSesion(domain.getLugarSesion() != null ? domain.getLugarSesion() : "");
        entity.setLinkVirtual(domain.getLinkVirtual() != null ? domain.getLinkVirtual() : "");
        entity.setOrden(domain.getOrden());
    }
}
