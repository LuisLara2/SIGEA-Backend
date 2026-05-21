package com.zentry.sigea.module_actividad.services.usecases.estado_actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.EstadoActividadRequest;


/**
 * Caso de uso para crear un nuevo estado de actividad
 */

@Component
public class CrearEstadoActividadUseCase {

    private final IEstadoActividadRepository estadoActividadRepository;

    public CrearEstadoActividadUseCase(IEstadoActividadRepository estadoActividadRepository) {
        this.estadoActividadRepository = estadoActividadRepository;
    }

    public String execute(EstadoActividadRequest estadoActividadRequest) {
        EstadoActividadDomainEntity nuevaEstadoActividad = EstadoActividadDomainEntity.create(

            estadoActividadRequest.getCodigo(),
            estadoActividadRequest.getEtiqueta()
        );
        return estadoActividadRepository.save(nuevaEstadoActividad) ? String.format("El estado de actividad %s se registro correctamente", estadoActividadRequest.getEtiqueta()) : "Ocurrio un problema al registrar el estado de actividad";
    }
}
