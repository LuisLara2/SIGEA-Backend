package com.zentry.sigea.module_actividad.services.usecases.tipoactividad;

import java.time.LocalDateTime;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.core.repositories.TipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;

public class CrearTipoActividadUseCase {

    private final TipoActividadRepository tipoActividadRepositoryImpl;

    public CrearTipoActividadUseCase(TipoActividadRepository tipoActividadRepositoryImpl) {
        this.tipoActividadRepositoryImpl = tipoActividadRepositoryImpl;
    }

    public TipoActividad execute(TipoActividadRequest request) {

        validateBusinessRules(request);

        TipoActividad tipoActividad = TipoActividad.create(
            request.getNombreActividad(), request.getDescripcion(), LocalDateTime.now(), LocalDateTime.now());

        return tipoActividad;
    }

    private void validateBusinessRules(TipoActividadRequest request) {

        // var actividadesExistentes = tipoActividadRepository.findByNombreActividad(request.getNombreActividad());
        // if (!actividadesExistentes.isEmpty()) {
        //     throw new IllegalArgumentException("Ya existe un tipo de actividad con ese nombre");
        // }
    }
    
    
}
