package com.zentry.sigea.module_actividad.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.TipoActividadEntity;

public interface TipoActividadJPARepository extends JpaRepository<TipoActividadEntity , UUID>{
    public Optional<TipoActividadEntity> findById(UUID id);
}
