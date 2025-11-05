package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.presentation.models.requestDTO.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.CrearActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.responseDTO.ActividadResponse;

public interface IActividad {
    public String crearActividad(CrearActividadRequest request);
    public ActividadResponse actualizarActividad(String id, ActividadRequest request);
    public void eliminarActividad(String id);
    public ActividadResponse obtenerActividadPorId(String id);
    public List<ActividadResponse> obtenerActividadesPorTipo(String tipoActividadId);
    public List<ActividadResponse> listarActividades();
}
