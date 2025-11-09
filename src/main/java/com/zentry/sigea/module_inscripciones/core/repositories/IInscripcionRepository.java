package com.zentry.sigea.module_inscripciones.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_inscripciones.core.entities.InscripcionDomainEntity;

/**
 * Repositorio del dominio para la gestión de inscripciones
 */
public interface IInscripcionRepository {
    public boolean save(InscripcionDomainEntity inscripcionDomainEntity);
    public Optional<InscripcionDomainEntity> findById(String id);
    public List<InscripcionDomainEntity> findByUsuarioId(String usuarioId);
    public List<InscripcionDomainEntity> findByActividadId(String actividadId);
    public List<InscripcionDomainEntity> findAll();
    public Optional<InscripcionDomainEntity> findByUsuarioIdAndActividadId(String usuarioId, String actividadId);
    public List<InscripcionDomainEntity> findByEstadoInscripcionId(String estadoId);
    public boolean existsByUsuarioIdAndActividadId(String usuarioId, String actividadId);
    public boolean existsById(String id);
    public void deleteById(String id);
}

