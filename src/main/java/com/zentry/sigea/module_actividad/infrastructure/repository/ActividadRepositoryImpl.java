package com.zentry.sigea.module_actividad.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.repositories.ActividadRepository;

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
    public Actividad save(Actividad actividad) {
        // Implementación temporal - retorna la misma actividad con ID generado
        if (actividad.getId() == null) {
            // Simular ID generado
            return new Actividad(
                1L, // ID temporal
                actividad.getTitle(),
                actividad.getDescription(),
                actividad.getStartDate(),
                actividad.getEndDate(),
                actividad.getStatusId(),
                actividad.getOrganizerId(),
                actividad.getTypeId(),
                actividad.getLocation(),
                actividad.getCreatedAt(),
                actividad.getUpdatedAt()
            );
        }
        return actividad;
    }

    @Override
    public Optional<Actividad> findById(Long id) {
        // Implementación temporal - retorna vacío
        return Optional.empty();
    }

    @Override
    public List<Actividad> findAll() {
        // Implementación temporal - retorna lista vacía
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo");
        }

        // ver si el usuario existe antes de eliminar
        if (!jpaRepository.existsById(id)) {
            throw new IllegalArgumentException("La actividad con ID " + id + " no existe");
        }


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