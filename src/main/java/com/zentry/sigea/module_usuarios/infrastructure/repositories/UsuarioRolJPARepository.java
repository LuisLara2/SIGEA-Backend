package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_usuarios.infrastructure.database.embedded.UsuarioRolId;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioRolEntity;

public interface UsuarioRolJPARepository extends JpaRepository<UsuarioRolEntity , UsuarioRolId>{
    public List<UsuarioRolEntity> findById_IdUsuario(UUID id);

    // El naming se basa en los nombres de los atributos
    // Spring Data JPA genera consultas basándose en la estructura de tus clases de entidad, 
    // no directamente en los nombres de las columnas de la base de datos.
    // La clase embebida tiene lo siguiente idUsuario e idRol y debo usar eso
    // Antes usaba UsuarioId y RolId, simplemente JPA no los encaontraba
    public LocalDateTime findAsignadoEnById_IdUsuarioAndId_IdRol(UUID idUsuario , UUID idRol);
}
