package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

public interface UsuarioJPARepository extends JpaRepository<UsuarioEntity , UUID>{
    public Optional<UsuarioEntity> findById(UUID id);
    public Optional<UsuarioEntity> findByCorreo(String correo);

    @Query("SELECT u.id FROM UsuarioEntity u WHERE u.correo = :correo")
    public Optional<UUID> findIdByCorreo(@Param("correo") String correo);
}