package com.zentry.sigea.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.infrastructure.database.embedded.UsuarioRolId;
import com.zentry.sigea.infrastructure.database.entities.UsuarioRolEntity;

public interface UsuarioRolJPARepository extends JpaRepository<UsuarioRolEntity , UsuarioRolId>{
    public List<UsuarioRolEntity> findById_UsuarioId(UUID id);
    public String findAsignadoEnById_UsuarioIdAndId_RolId(UUID usuarioId , UUID rolId);
}
