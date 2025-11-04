package com.zentry.sigea.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.infrastructure.database.entities.EstadoInscripcionEntity;

public interface EstadoInscripcionJPARepository extends JpaRepository<EstadoInscripcionEntity , UUID>{
    public Optional<EstadoInscripcionEntity> findById(UUID id);
}
