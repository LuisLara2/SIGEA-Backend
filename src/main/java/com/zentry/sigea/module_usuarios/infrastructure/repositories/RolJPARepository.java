package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.RolEntity;

public interface RolJPARepository extends JpaRepository<RolEntity , UUID>{
    public List<RolEntity> findAll();
    public Optional<RolEntity> findById(UUID id);
}
