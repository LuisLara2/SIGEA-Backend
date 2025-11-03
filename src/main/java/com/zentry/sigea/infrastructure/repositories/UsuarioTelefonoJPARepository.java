package com.zentry.sigea.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.infrastructure.database.entities.UsuarioTelefonoEntity;

public interface UsuarioTelefonoJPARepository extends JpaRepository<UsuarioTelefonoEntity , UUID>{
    public List<UsuarioTelefonoEntity> findAllByUsuarioId(UUID usuarioId);
    public List<UsuarioTelefonoEntity> findAllById(UUID id);
}
