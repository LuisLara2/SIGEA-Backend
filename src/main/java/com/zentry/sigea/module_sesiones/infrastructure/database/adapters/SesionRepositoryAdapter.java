package com.zentry.sigea.module_sesiones.infrastructure.database.adapters;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_actividad.infrastructure.repository.ActividadJPARepository;
import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import com.zentry.sigea.module_sesiones.infrastructure.database.mappers.SesionMapper;
import com.zentry.sigea.module_sesiones.infrastructure.repositories.spring.SesionJpaRepository;

/**
 * Adaptador que implementa el repositorio del dominio usando JPA
 * Este es el puente entre el dominio y la infraestructura (Hexagonal Architecture)
 */
@Repository
public class SesionRepositoryAdapter implements ISesionRepository {

    private final SesionJpaRepository sesionJpaRepository;
    private final ActividadJPARepository actividadJpaRepository;

    public SesionRepositoryAdapter(
        SesionJpaRepository sesionJpaRepository,
        ActividadJPARepository actividadJpaRepository
    ) {
        this.sesionJpaRepository = sesionJpaRepository;
        this.actividadJpaRepository = actividadJpaRepository;
    }

    @Override
    public SesionDomainEntity save(SesionDomainEntity sesion) {
        ActividadEntity actividadEntity = actividadJpaRepository
            .findById(UUID.fromString(sesion.getActividadId()))
            .orElseThrow(() -> new IllegalArgumentException(
                "No se encontró actividad con ID: " + sesion.getActividadId()
            ));

        var sesionEntity = SesionMapper.toEntity(sesion, actividadEntity);
        var savedEntity = sesionJpaRepository.save(sesionEntity);
    
        return SesionMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SesionDomainEntity> findById(String id) {
        return sesionJpaRepository
            .findById(UUID.fromString(id))
            .map(SesionMapper::toDomain);
    }

    @Override
    public List<SesionDomainEntity> findAll() {
        return sesionJpaRepository.findAll()
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<SesionDomainEntity> findByActividadId(String actividadId) {
        return sesionJpaRepository.findByActividadId(UUID.fromString(actividadId))
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<SesionDomainEntity> findByFechaRange(LocalDateTime inicio, LocalDateTime fin) {
        return sesionJpaRepository.findByFechaSesionBetween(inicio, fin)
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        return sesionJpaRepository.existsById(UUID.fromString(id));
    }

    @Override
    public void deleteById(String id) {
        sesionJpaRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public long countByActividadId(String actividadId) {
        return sesionJpaRepository.countByActividadId(UUID.fromString(actividadId));
    }
}