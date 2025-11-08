package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zentry.sigea.module_usuarios.infrastructure.database.embedded.UsuarioRolId;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioRolEntity;

public interface UsuarioRolJPARepository extends JpaRepository<UsuarioRolEntity , UsuarioRolId>{
    public List<UsuarioRolEntity> findById_IdUsuario(UUID id);

    // El naming se basa en los nombres de los atributos
    // Spring Data JPA genera consultas basándose en la estructura de tus clases de entidad, 
    // no directamente en los nombres de las columnas de la base de datos.
    // La clase embebida tiene lo siguiente idUsuario e idRol y debo usar eso
    // Antes usaba UsuarioId y RolId, simplemente JPA no los encaontraba

    // ppodemos usar una Query para seleccionar solo campos especificos y no toda la entidad
    // Aqui no necestio nativeQuery = true, ya que uso los nombres de atributos y clases de mi entity en java
    // si usara nativeQuery = true, tendria que usar nombres de mi BD como si fuera una consulta desde mi BD.
    @Query("SELECT ur.asignadoEn FROM UsuarioRolEntity ur WHERE ur.id.idUsuario = :idUsuario AND ur.id.idRol = :idRol")
    public LocalDateTime findAsignadoEnById_IdUsuarioAndId_IdRol(@Param("idUsuario") UUID idUsuario , @Param("idRol") UUID idRol);

    // Recuerda siempre colocar alias como "ur" para resumir nombres de tablas
    // Y siempre usalas al acceder a propiedades antes y despues
}
