package com.zentry.sigea.module_inscripciones.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_inscripciones.core.entities.EstadoInscripcionDomainEntity;

/**
 * Repositorio del dominio para la gestión de estados de inscripción
 */
public interface IEstadoInscripcionRepository {
    public Optional<EstadoInscripcionDomainEntity> findById(String id);
    public Optional<EstadoInscripcionDomainEntity> findByCodigo(String codigo);
    public List<EstadoInscripcionDomainEntity> findAll();
    public boolean save(EstadoInscripcionDomainEntity estadoInscripcionDomainEntity);
    public void deleteById(String id);
}

