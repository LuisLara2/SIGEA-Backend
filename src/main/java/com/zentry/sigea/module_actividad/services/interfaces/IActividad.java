package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.actividad.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.presentation.models.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.ActividadResponse;
import com.zentry.sigea.module_actividad.presentation.models.CrearActividadRequest;

public interface IActividad {
    ActividadDomainEntity crearActividad(CrearActividadRequest request);
    ActividadResponse actualizarActividad(Long id, ActividadRequest request);
    void eliminarActividad(Long id);
    ActividadResponse obtenerActividadPorId(Long id);
    List<ActividadResponse> obtenerActividadesPorTipo(Long tipoActividadId);
    List<ActividadResponse> listarActividades();

}
