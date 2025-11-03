package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;

public interface ITipoActividad {

    TipoActividad crearTipoActividad(TipoActividadRequest tipoActividad);
    TipoActividad actualizarTipoActividad(TipoActividadRequest tipoActividad);
    void eliminarTipoActividad(Long id);
    TipoActividad obtenerTipoActividadPorId(Long id);
    List<TipoActividad> listarTiposActividad();

}
