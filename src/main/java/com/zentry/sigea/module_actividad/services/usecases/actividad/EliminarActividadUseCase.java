package com.zentry.sigea.module_actividad.services.usecases.actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.repositories.IActividadRespository;

@Component
public class EliminarActividadUseCase {

    private final IActividadRespository actividadRepository;

    public EliminarActividadUseCase(IActividadRespository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public void execute(String id) {
        if (!actividadRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe actividad con ID: " + id);
        }
        actividadRepository.deleteById(id);
    }
}
