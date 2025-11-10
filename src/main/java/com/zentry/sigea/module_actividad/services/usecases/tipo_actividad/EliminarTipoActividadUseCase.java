package com.zentry.sigea.module_actividad.services.usecases.tipo_actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;



@Component
public class EliminarTipoActividadUseCase {
    
    private final ITipoActividadRepository tipoActividadRepository;
    public EliminarTipoActividadUseCase(ITipoActividadRepository tipoActividadRepository) {
        this.tipoActividadRepository = tipoActividadRepository;
    }

    public void execute(String id) {


        if (!tipoActividadRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Tipo de actividad no encontrado con ID: " + id);
        }
        tipoActividadRepository.deleteById(id);

    }
}
