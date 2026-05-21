package com.zentry.sigea.module_actividad.services.usecases.estado_actividad;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.EstadoActividadRequest;

@Component
public class ActualizarEstadoActividadUseCase {

    private final IEstadoActividadRepository estadoActividadRepository;

    public ActualizarEstadoActividadUseCase(IEstadoActividadRepository estadoActividadRepository) {
        this.estadoActividadRepository = estadoActividadRepository;
    }

    public EstadoActividadDomainEntity execute(String id, EstadoActividadRequest nuevoEstado) {
        Optional<EstadoActividadDomainEntity> actividadOpt = estadoActividadRepository.findById(id);
        if (actividadOpt.isEmpty()) {
            throw new IllegalArgumentException("Actividad no encontrada con ID: " + id);
        }

        EstadoActividadDomainEntity estadoActividad = actividadOpt.get();

        estadoActividad.setCodigo(nuevoEstado.getCodigo());
        estadoActividad.setEtiqueta(nuevoEstado.getEtiqueta());

        estadoActividadRepository.save(estadoActividad);
        return estadoActividad;
    }

}
