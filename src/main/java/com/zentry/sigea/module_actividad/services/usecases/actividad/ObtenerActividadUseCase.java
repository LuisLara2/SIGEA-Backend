package com.zentry.sigea.module_actividad.services.usecases.actividad;

import java.util.Optional;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.repositories.ActividadRepository;

/**
 * Caso de uso para obtener una actividad por su ID
 * Implementa el patrón Query siguiendo principios CQRS
 */
public class ObtenerActividadUseCase {
    
    private final ActividadRepository actividadRepository;

    public ObtenerActividadUseCase(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public Optional<Actividad> execute(Long actividadId) {
        if (actividadId == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo");
        }
        
        return actividadRepository.findById(actividadId);
    }
}