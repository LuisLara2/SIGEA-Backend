package com.zentry.sigea.module_sesiones.infrastructure.repositories.spring;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA de Spring Data para SesionEntity
 */
@Repository
public interface SesionJpaRepository extends JpaRepository<SesionEntity, UUID> {
    
    List<SesionEntity> findByActividadId(UUID actividadId);
    List<SesionEntity> findByFechaSesionBetween(LocalDateTime inicio, LocalDateTime fin);
    long countByActividadId(UUID actividadId);
    boolean existsByActividadIdAndFechaSesion(UUID actividadId, LocalDateTime fechaSesion);
}