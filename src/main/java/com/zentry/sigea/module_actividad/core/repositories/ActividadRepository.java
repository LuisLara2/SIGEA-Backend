package com.zentry.sigea.module_actividad.core.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;

public interface ActividadRepository {
    Actividad save(Actividad actividad);
    Optional<Actividad> findById(Long id);
    List<Actividad> findAll();
    List<Actividad> findByOrganizerId(Long organizerId);
    List<Actividad> findByStatusId(Long statusId);
    List<Actividad> findByDateRange(LocalDate startDate, LocalDate endDate);
    List<Actividad> findActiveActivities();
    boolean existsById(Long id);
    void deleteById(Long id);
}