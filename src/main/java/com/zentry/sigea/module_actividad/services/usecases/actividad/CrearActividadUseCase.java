package com.zentry.sigea.module_actividad.services.usecases.actividad;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.actividad.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.ActividadRepository;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.core.repositories.TipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.CrearActividadRequest;

/**
 * Caso de uso para crear una nueva actividad
*/
@Component
public class CrearActividadUseCase {

    private final ActividadRepository actividadRepository;
    private final IEstadoActividadRepository estadoActividadRepository;
    private final TipoActividadRepository tipoActividadRepository;

    public CrearActividadUseCase(ActividadRepository actividadRepository,
                               IEstadoActividadRepository estadoActividadRepository,
                               TipoActividadRepository tipoActividadRepository) {
        this.actividadRepository = actividadRepository;
        this.estadoActividadRepository = estadoActividadRepository;
        this.tipoActividadRepository = tipoActividadRepository;
    }

    /**
     * Ejecuta la creación de actividad recibiendo IDs y convirtiéndolos a objetos completos
     */
    public ActividadDomainEntity execute(CrearActividadRequest request) {
        // Validaciones básicas de entrada
        validateBasicFields(request);
        
        // Obtener objetos por ID y validar que existan
        EstadoActividadDomainEntity estado = getEstadoActividadById(request.getEstadoId());
        TipoActividadDomainEntity tipoActividad = getTipoActividadById(request.getTipoActividadId());
        
        // Validaciones de negocio específicas del caso de uso
        validateBusinessRules(request);
        
        // Crear la entidad usando el factory method del dominio
        ActividadDomainEntity nuevaActividad = ActividadDomainEntity.create(
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaInicio(),
            request.getFechaFin(),
            estado,
            request.getOrganizadorId(),
            tipoActividad,
            request.getUbicacion()
        );
        
        // Guardar usando el repositorio directamente
        return actividadRepository.save(nuevaActividad);
    }

    /**
     * Obtiene un EstadoActividad por su ID
     */
    private EstadoActividadDomainEntity getEstadoActividadById(Long estadoId) {
        if (estadoId == null || estadoId <= 0) {
            throw new IllegalArgumentException("El ID del estado de actividad debe ser un número positivo");
        }

        EstadoActividadDomainEntity estado = estadoActividadRepository.findById(estadoId);
        if (estado == null) {
            throw new IllegalArgumentException(
                "No se encontró un estado de actividad con ID: " + estadoId
            );
        }

        return estado;
    }

    /**
     * Obtiene un TipoActividad por su ID
     */
    private TipoActividadDomainEntity getTipoActividadById(Long tipoActividadId) {
        if (tipoActividadId == null || tipoActividadId <= 0) {
            throw new IllegalArgumentException("El ID del tipo de actividad debe ser un número positivo");
        }

        TipoActividadDomainEntity tipoActividad = tipoActividadRepository.findById(tipoActividadId);
        if (tipoActividad == null) {
            throw new IllegalArgumentException(
                "No se encontró un tipo de actividad con ID: " + tipoActividadId
            );
        }

        return tipoActividad;
    }

    private void validateBusinessRules(CrearActividadRequest request) {
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
    
    private void validateBasicFields(CrearActividadRequest request) {
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
        
        if (request.getEstadoId() == null || request.getEstadoId() <= 0) {
            throw new IllegalArgumentException("El ID del estado debe ser un número positivo");
        }
        
        if (request.getTipoActividadId() == null || request.getTipoActividadId() <= 0) {
            throw new IllegalArgumentException("El ID del tipo de actividad debe ser un número positivo");
        }
    }
    
    private void validateDates(CrearActividadRequest request) {
        if (request.getFechaFin().isBefore(request.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        
        if (request.getFechaInicio().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se pueden crear actividades en fechas pasadas");
        }
    }
    
    private void validateDateConflicts(CrearActividadRequest request) {
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


