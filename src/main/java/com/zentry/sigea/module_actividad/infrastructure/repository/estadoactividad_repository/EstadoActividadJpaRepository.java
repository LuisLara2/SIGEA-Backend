package com.zentry.sigea.module_actividad.infrastructure.repository.estadoactividad_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.EstadoActividadEntity;

public interface EstadoActividadJpaRepository extends JpaRepository<EstadoActividadEntity, Long>, JpaSpecificationExecutor<EstadoActividadEntity> {
    // filtros opcionales por estado/tipo: usar Specification si quieres
}
