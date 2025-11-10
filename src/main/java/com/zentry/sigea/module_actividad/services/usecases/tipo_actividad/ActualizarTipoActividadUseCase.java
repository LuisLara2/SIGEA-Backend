package com.zentry.sigea.module_actividad.services.usecases.tipo_actividad;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.TipoActividadRequest;

@Component
public class ActualizarTipoActividadUseCase {

    private final ITipoActividadRepository tipoActividadRepository;

    public ActualizarTipoActividadUseCase(ITipoActividadRepository tipoActividadRepository) {
        this.tipoActividadRepository = tipoActividadRepository;
    }

    public TipoActividadDomainEntity execute(String id, TipoActividadRequest request) {
        Optional<TipoActividadDomainEntity> tipoOpt = tipoActividadRepository.findById(id);
        if (tipoOpt.isEmpty()) {
            throw new IllegalArgumentException("Tipo de actividad no encontrado con ID: " + id);
        }

        TipoActividadDomainEntity tipo = tipoOpt.get();
        tipo.setNombreActividad(request.getNombreActividad());
        tipo.setDescripcion(request.getDescripcion());
        tipo.setUpdatedAt(java.time.LocalDateTime.now());

        tipoActividadRepository.save(tipo);
        return tipo;
    }
}
