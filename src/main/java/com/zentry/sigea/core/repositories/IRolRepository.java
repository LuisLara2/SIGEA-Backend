package com.zentry.sigea.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.core.entities.RolDomainEntity;

public interface IRolRepository {
    public List<RolDomainEntity> findAll();
    public Optional<RolDomainEntity> findById(String id);
}
