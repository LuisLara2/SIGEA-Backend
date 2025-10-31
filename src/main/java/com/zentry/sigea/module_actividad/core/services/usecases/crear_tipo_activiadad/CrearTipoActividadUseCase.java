package com.zentry.sigea.module_actividad.core.services.usecases.crear_tipo_activiadad;

import com.zentry.sigea.module_actividad.core.repositories.TipoActividadRepository;

public class CrearTipoActividadUseCase {

    private final TipoActividadRepository tipoActividadRepository;

    public CrearTipoActividadUseCase(TipoActividadRepository tipoActividadRepository) {
        this.tipoActividadRepository = tipoActividadRepository;
    }


    
}
