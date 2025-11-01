package com.zentry.sigea.module_actividad.infrastructure.repository.actividad_repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.repositories.ActividadRepository;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_actividad.infrastructure.database.mappers.ActividadMapper;

/**
 * Implementación del repositorio de Actividad siguiendo DDD
 * Implementa la interfaz del core usando JPA
 */
@Repository
public class ActividadRepositoryImpl implements ActividadRepository {

    private final ActividadJpaRepository jpaRepository;

    public ActividadRepositoryImpl(ActividadJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Actividad> findAll() {
        List<ActividadEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(ActividadMapper::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public Actividad save(Actividad actividad) {
        ActividadEntity entity = ActividadMapper.toEntity(actividad);
        ActividadEntity savedEntity = jpaRepository.save(entity);
        return ActividadMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Actividad> findById(Long id) {

        Optional<ActividadEntity> entity = jpaRepository.findById(id);
        return entity.map(ActividadMapper::toDomain);
    }


    @Override
    public void deleteById(Long id) {

        jpaRepository.deleteById(id);
    }

    @Override
    public List<Actividad> findByOrganizerId(Long organizerId) {
        // Implementación temporal - retorna lista vacía

        return List.of();
    }

    @Override
    public List<Actividad> findByStatusId(Long statusId) {
        // Implementación temporal - retorna lista vacía

        if (statusId == null) {
            throw new IllegalArgumentException("El ID de estado no puede ser nulo");
        }
        return List.of();
    }

    @Override
    public List<Actividad> findByDateRange(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        // Implementación temporal - retorna lista vacía

        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Rango de fechas inválido");
        }

        return List.of();
    }

    @Override
    public List<Actividad> findActiveActivities() {
        // Implementación temporal - retorna lista vacía
        return List.of();
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}