package com.zentry.sigea.module_inscripciones.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_inscripciones.infrastructure.database.entities.EstadoInscripcionEntity;

public interface EstadoInscripcionJPARepository extends JpaRepository<EstadoInscripcionEntity, UUID> {
    public Optional<EstadoInscripcionEntity> findByCodigo(String codigo);
}

