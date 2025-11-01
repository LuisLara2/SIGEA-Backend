package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import com.zentry.sigea.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;

/**
 * Mapper para convertir entre Actividad (dominio) y ActividadEntity (JPA)
 * 
 * NOTA: Este mapper tiene problemas de mapeo que necesitan ser resueltos:
 * 1. La entidad de dominio usa tipos como EstadoActividad y TipoActividad (objetos valor)
 * 2. La entidad JPA usa entidades relacionadas (EstadoActividadEntity, TipoActividadEntity)
 * 3. Necesitamos mappers adicionales para estos objetos valor
 */
public class ActividadMapper {

    // TODO: Este método necesita ser refactorizado cuando se implementen los mappers para EstadoActividad y TipoActividad
    public static Actividad toDomain(ActividadEntity entity) {
        if (entity == null) {
            return null;
        }
        
        // Mapear TipoActividad usando el mapper existente
        TipoActividad tipoActividad = null;
        if (entity.getTipoActividad() != null) {
            tipoActividad = TipoActividadMapper.toDomain(entity.getTipoActividad());
        }
        
        // Mapear EstadoActividad usando el mapper existente
        EstadoActividad estadoActividad = null;
        if (entity.getEstadoActividad() != null) {
            estadoActividad = EstadoActividadMapper.toDomain(entity.getEstadoActividad());
        }
        
        return new Actividad(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getFechaInicio(),
            entity.getFechaFin(),
            estadoActividad,           
            entity.getOrganizador().getId(), // Extraer solo el ID del organizador
            tipoActividad,             
            entity.getLugar(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    // TODO: Este método también necesita ser refactorizado
    public static ActividadEntity toEntity(Actividad domain) {
        if (domain == null) {
            return null;
        }
        

        // Para implementarlo correctamente necesitarías:
        ActividadEntity entity = new ActividadEntity();
        entity.setId(domain.getId());
        entity.setTitulo(domain.getTitle());
        entity.setDescripcion(domain.getDescription());
        entity.setFechaInicio(domain.getStartDate());
        entity.setFechaFin(domain.getEndDate());
        entity.setEstadoActividad(estadoActividadRepository.findById(domain.getStatusId()));
        // entity.setOrganizador(usuarioRepository.findById(domain.getOrganizerId()));
        entity.setTipoActividad(tipoActividadRepository.findById(domain.getTypeId()));
        entity.setLugar(domain.getLocation());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
        
    }
}
