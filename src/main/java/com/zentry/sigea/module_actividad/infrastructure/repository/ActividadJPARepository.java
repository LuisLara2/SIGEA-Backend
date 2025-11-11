package com.zentry.sigea.module_actividad.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;

public interface ActividadJPARepository extends JpaRepository<ActividadEntity , UUID>{
    public Optional<ActividadEntity> findById(UUID id);
    public List<ActividadEntity> findAll();
    public List<ActividadEntity> findByOrganizadorId(UUID organizadorId);
    public List<ActividadEntity> findByEstadoActividadId(UUID estadoActividadId);
    public List<ActividadEntity> findByTipoActividadId(UUID tipoActividadId);
    public List<ActividadEntity> findByFechaInicioBetween(LocalDate fechaInicio , LocalDate fechaFin);
    public List<ActividadEntity> findByEstadoActividad_Codigo(String codigo);
}