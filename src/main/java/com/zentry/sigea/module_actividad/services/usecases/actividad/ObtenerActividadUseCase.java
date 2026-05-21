package com.zentry.sigea.module_actividad.services.usecases.actividad;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IActividadRespository;

/**
 * Caso de uso para obtener una actividad por su ID
 * Implementa el patrón Query siguiendo principios CQRS
 */
@Component
public class ObtenerActividadUseCase {
    
    private final IActividadRespository actividadRepository;

    public ObtenerActividadUseCase(IActividadRespository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public Optional<ActividadDomainEntity> execute(String actividadId) {
        if (actividadId == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo");
        }
        
        return actividadRepository.findById(actividadId);
    }
}