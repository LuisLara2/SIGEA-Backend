package com.zentry.sigea.module_certificacion.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_certificacion.core.entities.ValidacionDomainEntity;

public interface IValidacionRepository {
    public Optional<ValidacionDomainEntity> findById(String id);
    public Optional<ValidacionDomainEntity> findByCertificadoId(String certificadoId);
    public void save(ValidacionDomainEntity validacionDomainEntity);
    public void saveAll(List<ValidacionDomainEntity> listValidacionDomainEntities);
}
