package com.zentry.sigea.module_actividad.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.EstadoActividadEntity;

public interface EstadoActividadJPARepository extends JpaRepository<EstadoActividadEntity , UUID>{
    public Optional<EstadoActividadEntity> findById(UUID id);
    public Optional<EstadoActividadEntity> findByCodigo(String codigo);
    public List<EstadoActividadEntity> findAll();
}