package com.zentry.sigea.module_actividad.core.usecases.actividad;

import java.time.LocalDate;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.repositories.ActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.ActividadRequest;

public class CrearActividadUseCase {

    private final ActividadRepository actividadRepository;

    public CrearActividadUseCase(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public Actividad execute (ActividadRequest request) {
        // Validaciones de negocio específicas del caso de uso
        validateBusinessRules(request);
        
        // Crear la entidad usando el factory method del dominio
        Actividad nuevaActividad = Actividad.create(
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaInicio(),
            request.getFechaFin(),
            request.getEstado(),
            request.getOrganizadorId(),
            request.getTipoActividad(),
            request.getUbicacion()
        );
        
        // Guardar usando el repositorio directamente
        return actividadRepository.save(nuevaActividad);
    }

    private void validateBusinessRules(ActividadRequest request) {
        // Validaciones básicas
        validateBasicFields(request);
        
        // Validar fechas
        validateDates(request);
        
        // Validar que no haya conflictos de fechas para el organizador
        validateDateConflicts(request);
        
        // Validar que la fecha de inicio no sea muy lejana en el futuro
        if (request.getFechaInicio().isAfter(LocalDate.now().plusYears(2))) {
            throw new IllegalArgumentException(
                "No se pueden crear actividades con más de 2 años de anticipación"
            );
        }
    }
    
    private void validateBasicFields(ActividadRequest request) {
        if (request.getTitulo() == null || request.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
        
        if (request.getFechaInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        }
        
        if (request.getFechaFin() == null) {
            throw new IllegalArgumentException("La fecha de fin es obligatoria");
        }
        
        if (request.getOrganizadorId() == null || request.getOrganizadorId() <= 0) {
            throw new IllegalArgumentException("El ID del organizador debe ser un número positivo");
        }
    }
    
    private void validateDates(ActividadRequest request) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        
        if (request.getFechaInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden crear actividades en fechas pasadas");
        }
    }
    
    private void validateDateConflicts(ActividadRequest request) {
        var actividadesExistentes = actividadRepository.findByOrganizerId(request.getOrganizadorId());

        boolean hasConflict = actividadesExistentes.stream()
            .anyMatch(actividad -> 
                datesOverlap(
                    actividad.getStartDate(), actividad.getEndDate(),
                    request.getFechaInicio(), request.getFechaFin()
                )
            );
            
        if (hasConflict) {
            throw new IllegalArgumentException(
                "El organizador ya tiene una actividad programada en las fechas seleccionadas"
            );
        }
    }

    private boolean datesOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return !end1.isBefore(start2) && !start1.isAfter(end2);
    }
}


