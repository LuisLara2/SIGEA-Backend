package com.zentry.sigea.module_actividad.services.usecases.actividad;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IActividadRespository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.ActividadRequest;

@Component
public class ActualizarActividadUseCase {

    private final IActividadRespository actividadRepository;

    public ActualizarActividadUseCase(IActividadRespository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public ActividadDomainEntity execute(String id, ActividadRequest request) {
        Optional<ActividadDomainEntity> actividadOpt = actividadRepository.findById(id);
        if (actividadOpt.isEmpty()) {
            throw new IllegalArgumentException("Actividad no encontrada con ID: " + id);
        }

        ActividadDomainEntity actividad = actividadOpt.get();

        actividad.updateInfo(
                request.getTitulo(),
                request.getDescripcion(),
                request.getFechaInicio(),
                request.getFechaFin(),
                request.getUbicacion());

        if (request.getEstado() != null) {
            actividad.changeStatus(request.getEstado());
        }
        if (request.getTipoActividad() != null) {
            actividad.setTipoActividadDomainEntity(request.getTipoActividad());
        }

        actividadRepository.save(actividad);
        return actividad;
    }
}
