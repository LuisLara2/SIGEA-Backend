package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import com.zentry.sigea.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;

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
        
        return new Actividad(
            entity.getId(),
            entity.getTitulo(),
            entity.getDescripcion(),
            entity.getFechaInicio(),
            entity.getFechaFin(),
            // TODO: Mapear entity.getEstadoActividad() a EstadoActividad
            null, // statusId - necesita mapper de EstadoActividadEntity a EstadoActividad
            entity.getOrganizador().getId(), // Asumiendo que queremos solo el ID
            // TODO: Mapear entity.getTipoActividad() a TipoActividad  
            null, // typeId - necesita mapper de TipoActividadEntity a TipoActividad
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
        
        // Por ahora, comentamos este método hasta reso
        
        
        return new ActividadEntity(
            domain.getId(),
            domain.getTitle(),
            domain.getDescription(),
            domain.getStartDate(),
            domain.getEndDate(),
            // TODO: Resolver EstadoActividadEntity desde EstadoActividad
            null, // estadoActividad
            // TODO: Resolver UsuarioEntity desde organizerId
            null, // organizador
            // TODO: Resolver TipoActividadEntity desde TipoActividad
            null, // tipoActividad
            domain.getLocation(),
            domain.getCreatedAt(),
            domain.getUpdatedAt()
        );
        
    }
}
