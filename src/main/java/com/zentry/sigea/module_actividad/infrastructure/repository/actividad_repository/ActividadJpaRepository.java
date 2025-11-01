package com.zentry.sigea.module_actividad.infrastructure.repository.actividad_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;

/**
 * Repositorio JPA para la entidad ActividadEntity
 * Extiende JpaRepository para operaciones CRUD básicas
 */
// infrastructure/database/repositories/ActividadJpaRepository.java
public interface ActividadJpaRepository extends JpaRepository<ActividadEntity, Long>, JpaSpecificationExecutor<ActividadEntity> {
  // filtros opcionales por estado/tipo: usar Specification si quieres
}
