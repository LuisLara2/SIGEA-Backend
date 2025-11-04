package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;

public interface ITipoActividad {

    TipoActividadDomainEntity crearTipoActividad(TipoActividadRequest tipoActividad);
    TipoActividadDomainEntity actualizarTipoActividad(TipoActividadRequest tipoActividad);
    void eliminarTipoActividad(Long id);
    TipoActividadDomainEntity obtenerTipoActividadPorId(Long id);
    List<TipoActividadDomainEntity> listarTiposActividad();

}
