package com.zentry.sigea.module_usuarios.core.repositories;

import java.util.Optional;

import com.zentry.sigea.module_usuarios.core.entities.EstadoInscripcionDomainEntity;

public interface IEstadoInscripcionRepository {
    public Optional<EstadoInscripcionDomainEntity> findById(String id);
    public boolean save(EstadoInscripcionDomainEntity estadoInscripcionDomainEntity);
    public boolean deleteById(String id);
}
