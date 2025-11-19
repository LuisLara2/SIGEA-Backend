package com.zentry.sigea.module_certificacion.core.repositories;

import java.util.Optional;

import com.zentry.sigea.module_certificacion.core.entities.TipoValidadorDomainEntity;

public interface ITipoValidadorRepository {
    public Optional<TipoValidadorDomainEntity> findById(String id);
    public void save(TipoValidadorDomainEntity tipoValidadorDomainEntity);
}
