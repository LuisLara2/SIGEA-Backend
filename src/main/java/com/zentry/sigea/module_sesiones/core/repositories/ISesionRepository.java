package com.zentry.sigea.module_sesiones.core.repositories;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del repositorio de sesiones
 * Define el contrato que debe implementar la infraestructura
 */
public interface ISesionRepository {
    
    SesionDomainEntity save(SesionDomainEntity sesion);
    Optional<SesionDomainEntity> findById(String id);
    List<SesionDomainEntity> findAll();
    List<SesionDomainEntity> findByActividadId(String actividadId);
    List<SesionDomainEntity> findByFechaRange(LocalDateTime inicio, LocalDateTime fin);
    boolean existsById(String id);
    void deleteById(String id);
    long countByActividadId(String actividadId);
}