package com.zentry.sigea.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.core.entities.UsuarioDomainEntity;

public interface IUsuarioRepository {
    public void save(UsuarioDomainEntity usuario);
    public Optional<UsuarioDomainEntity> findById(String id);
    public List<UsuarioDomainEntity> findAll();
}
