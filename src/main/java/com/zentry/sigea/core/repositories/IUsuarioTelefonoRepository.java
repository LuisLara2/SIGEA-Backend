package com.zentry.sigea.core.repositories;

import java.util.List;

import com.zentry.sigea.core.entities.UsuarioTelefonoDomainEntity;

public interface IUsuarioTelefonoRepository {
    public List<UsuarioTelefonoDomainEntity> findAllByUsuarioId(String usuarioId);
    public List<UsuarioTelefonoDomainEntity> findAllById(String id);
}
