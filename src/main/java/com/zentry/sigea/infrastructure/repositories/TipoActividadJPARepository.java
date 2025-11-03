package com.zentry.sigea.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.infrastructure.database.entities.TipoActividadEntity;

public interface TipoActividadJPARepository extends JpaRepository<TipoActividadEntity , UUID>{
    public Optional<TipoActividadEntity> findById(UUID id);
}
