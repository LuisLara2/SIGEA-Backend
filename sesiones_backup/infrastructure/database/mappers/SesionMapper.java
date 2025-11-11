package com.zentry.sigea.modules.sesiones.infrastructure.database.mappers;

import com.zentry.sigea.modules.sesiones.core.entities.Sesion;
import com.zentry.sigea.infrastructure.database.entities.SesionEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper entre la entidad de dominio `Sesion` (módulo) y la entidad JPA central `SesionEntity`.
 * Nota: la entidad JPA guarda una referencia a ActividadEntity; aquí convertimos esa referencia
 * a un simple activityId (Long) en el dominio.
 */
public class SesionMapper {

    public static Sesion toDomain(SesionEntity e) {
        if (e == null) return null;
        Long actividadId = null;
        if (e.getActividad() != null) {
            actividadId = e.getActividad().getId();
        }
        return Sesion.withId(e.getId(), actividadId, e.getFechaSesion(), e.getTitulo());
    }

    public static SesionEntity toEntity(Sesion s) {
        // La conversión a la entidad JPA no está soportada desde el mapper del módulo
        // porque la entidad JPA central tiene dependencias de infra (ActividadEntity) y
        // puede no exponer un constructor sin argumentos. Si se necesita persistir en
        // DB, el servicio de aplicación debe realizar el mapeo adecuado.
        throw new UnsupportedOperationException("toEntity no implementado en SesionMapper; use un servicio para mapear a SesionEntity");
    }

    public static List<Sesion> toDomainList(List<SesionEntity> entities) {
        return entities.stream().map(SesionMapper::toDomain).collect(Collectors.toList());
    }
}
