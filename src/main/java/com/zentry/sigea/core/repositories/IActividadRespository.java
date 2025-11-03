package com.zentry.sigea.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.core.entities.ActividadDomainEntity;

public interface IActividadRespository {
    public void save(ActividadDomainEntity actividadDomainEntity);
    public Optional<ActividadDomainEntity> findById(String id);
    public List<ActividadDomainEntity> findByOrganizadorId(String organizadorId);
}
