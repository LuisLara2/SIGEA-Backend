package com.zentry.sigea.module_sesiones.core.repositories;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interfaz del repositorio de sesiones
 * Define el contrato que debe implementar la infraestructura
 */
public interface ISesionRepository {
    
    SesionDomainEntity save(SesionDomainEntity sesion);
    Optional<SesionDomainEntity> findById(Long id);
    List<SesionDomainEntity> findAll();
    List<SesionDomainEntity> findByActividadId(UUID actividadId);
    List<SesionDomainEntity> findByFechaRange(LocalDateTime inicio, LocalDateTime fin);
    boolean existsById(Long id);
    void deleteById(Long id);
    long countByActividadId(UUID actividadId);
}