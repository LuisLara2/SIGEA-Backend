package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.InscripcionEntity;

public interface InscripcionJPARepository extends JpaRepository<InscripcionEntity , UUID>{
    public Optional<InscripcionEntity> findById(UUID id);
    public List<InscripcionEntity> findByActividadId(UUID actividadId);
    public List<InscripcionEntity> findByUsuarioId(UUID usuarioId);
}
