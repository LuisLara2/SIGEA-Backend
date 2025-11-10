package com.zentry.sigea.module_usuarios.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.AsistenciaEntity;

public interface AsistenciaJPARepository extends JpaRepository<AsistenciaEntity , UUID>{
    public Optional<AsistenciaEntity> findById(UUID id);
    public List<AsistenciaEntity> findByInscripcionId(UUID inscripcionId);
    public List<AsistenciaEntity> findBySesionId(UUID sesionId);
    public List<AsistenciaEntity> findBySesionIdAndPresente(UUID sesionId , Boolean presente);
}
