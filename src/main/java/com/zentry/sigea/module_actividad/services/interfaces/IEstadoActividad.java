package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.presentation.models.EstadoActividadRequest;

public  interface IEstadoActividad {
    EstadoActividad crearEstadoActividad(EstadoActividadRequest estadoActividad);
    EstadoActividad actualizarEstadoActividad(EstadoActividadRequest estadoActividad);
    void eliminarEstadoActividad(Long id);
    EstadoActividad obtenerEstadoActividadPorId(Long id);
    List<EstadoActividad> obtenerTodosLosEstadosActividad();
}