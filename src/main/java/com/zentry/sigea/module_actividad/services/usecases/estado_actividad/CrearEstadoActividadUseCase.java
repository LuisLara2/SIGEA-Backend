package com.zentry.sigea.module_actividad.services.usecases.estado_actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.core.repositories.EstadoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.EstadoActividadRequest;


/**
 * Caso de uso para crear un nuevo estado de actividad
 */

@Component
public class CrearEstadoActividadUseCase {

    private final EstadoActividadRepository estadoActividadRepository;

    public CrearEstadoActividadUseCase(EstadoActividadRepository estadoActividadRepository) {
        this.estadoActividadRepository = estadoActividadRepository;
    }

    public EstadoActividad execute(EstadoActividadRequest estadoActividadRequest) {
        EstadoActividad nuevaEstadoActividad = EstadoActividad.create(

            estadoActividadRequest.getCodigo(),
            estadoActividadRequest.getEtiqueta()
        );
        return estadoActividadRepository.save(nuevaEstadoActividad);
    }
}
