package com.zentry.sigea.module_usuarios.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;

public interface IRolRepository {
    public List<RolDomainEntity> findAll();
    public Optional<RolDomainEntity> findById(String id);
}
