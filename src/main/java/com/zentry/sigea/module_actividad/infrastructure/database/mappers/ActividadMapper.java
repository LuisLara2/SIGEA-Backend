package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;

/**
 * Mapper para convertir entre Actividad (dominio) y ActividadEntity (JPA)
 *
 * Nota: los mappers de EstadoActividad y TipoActividad se exponen como utilidades
 * con métodos estáticos (EstadoActividadMapper, TipoActividadMapper), por lo que
 * aquí los usamos directamente sin depender de repositorios ni beans adicionales.
 */
public class ActividadMapper {

    public static Actividad toDomain(ActividadEntity entity) {
        if (entity == null) {
            return null;
        }

        EstadoActividad estadoActividad = null;
        if (entity.getEstadoActividad() != null) {
            estadoActividad = EstadoActividadMapper.toDomain(entity.getEstadoActividad());
        }

        TipoActividad tipoActividad = null;
        if (entity.getTipoActividad() != null) {
            tipoActividad = TipoActividadMapper.toDomain(entity.getTipoActividad());
        }

        Long organizerId = null;
        if (entity.getOrganizador() != null) {
            organizerId = entity.getOrganizador().getId();
        }

        return new Actividad(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getFechaInicio(),
            entity.getFechaFin(),
            estadoActividad,
            organizerId,
            tipoActividad,
            entity.getLugar(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public static ActividadEntity toEntity(Actividad domain) {
        if (domain == null) {
            return null;
        }

        ActividadEntity entity = new ActividadEntity();
        entity.setId(domain.getId());
        entity.setTitulo(domain.getTitle());
        entity.setDescripcion(domain.getDescription());
        entity.setFechaInicio(domain.getStartDate());
        entity.setFechaFin(domain.getEndDate());

        // Convertir estado y tipo usando los mappers disponibles
        entity.setEstadoActividad(EstadoActividadMapper.toEntity(domain.getStatusId()));
        entity.setTipoActividad(TipoActividadMapper.toEntity(domain.getTypeId()));

        // Para la prueba solicitada: dejar el organizador como null (no referenciamos usuario)
        entity.setOrganizador(null);

        entity.setLugar(domain.getLocation());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());

        return entity;
    }
}
