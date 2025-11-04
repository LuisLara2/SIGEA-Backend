package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

public interface UsuarioJPARepository extends JpaRepository<UsuarioEntity , UUID>{
    public Optional<UsuarioEntity> findById(UUID id);
}
