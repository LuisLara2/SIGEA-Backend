package com.zentry.sigea.module_actividad.services.usecases.estado_actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;


@Component
public class EliminarEstadoActividadUseCase {


    private final IEstadoActividadRepository estadoActividadRepository;


    public EliminarEstadoActividadUseCase(IEstadoActividadRepository estadoActividadRepository) {
        this.estadoActividadRepository = estadoActividadRepository;
    }
    public void execute(String id) {

        if (!estadoActividadRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Estado de actividad no encontrado con ID: " + id);
        }

        
        estadoActividadRepository.deleteById(id);
    }

}
