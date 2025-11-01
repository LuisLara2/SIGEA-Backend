package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;

public  interface IEstadoActividad {
    EstadoActividad crearEstadoActividad(EstadoActividad estadoActividad);
    EstadoActividad actualizarEstadoActividad(EstadoActividad estadoActividad);
    void eliminarEstadoActividad(Long id);
    EstadoActividad obtenerEstadoActividadPorId(Long id);
    List<EstadoActividad> obtenerTodosLosEstadosActividad();
}