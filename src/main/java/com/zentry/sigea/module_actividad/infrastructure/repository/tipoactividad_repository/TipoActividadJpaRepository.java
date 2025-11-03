package com.zentry.sigea.module_actividad.infrastructure.repository.tipoactividad_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.TipoActividadEntity;

public interface TipoActividadJpaRepository extends JpaRepository<TipoActividadEntity, Long>, JpaSpecificationExecutor<TipoActividadEntity> {

}
