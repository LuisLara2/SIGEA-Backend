package com.zentry.sigea.module_certificacion.core.repositories;

import java.util.Optional;

import com.zentry.sigea.module_certificacion.core.entities.EstadoCertificadoDomainEntity;

public interface IEstadoCertificadoRepository {
    public Optional<EstadoCertificadoDomainEntity> findById(String id);
    public void save(EstadoCertificadoDomainEntity estadoCertificadoDomainEntity);
}
