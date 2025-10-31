package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.presentation.models.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.ActividadResponse;

public interface IActividad {
    Actividad crearActividad(ActividadRequest request);
    ActividadResponse actualizarActividad(Long id, ActividadRequest request);
    void eliminarActividad(Long id);
    ActividadResponse obtenerActividadPorId(Long id);
    List<ActividadResponse> obtenerTodasLasActividades();
    List<ActividadResponse> obtenerActividadesPorUsuario(Long usuarioId);
    List<ActividadResponse> obtenerActividadesPorTipo(Long tipoActividadId);

}
