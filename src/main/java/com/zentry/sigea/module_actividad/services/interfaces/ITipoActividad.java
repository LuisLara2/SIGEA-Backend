package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.TipoActividadRequest;

public interface ITipoActividad {
    public String crearTipoActividad(TipoActividadRequest tipoActividad);
    public TipoActividadDomainEntity actualizarTipoActividad(String id, TipoActividadRequest tipoActividad);
    public void eliminarTipoActividad(String id);
    public List<TipoActividadDomainEntity> listarTiposActividad();
    public TipoActividadDomainEntity obtenerTipoActividadPorId(String id);
}
