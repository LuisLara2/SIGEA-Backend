package com.zentry.sigea.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.infrastructure.database.entities.EstadoActividadEntity;

public interface EstadoActividadJPARepository extends JpaRepository<EstadoActividadEntity , UUID>{
    public Optional<EstadoActividadEntity> findById(UUID id);
}