package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.presentation.models.EstadoActividadRequest;

public  interface IEstadoActividad {
    EstadoActividadDomainEntity crearEstadoActividad(EstadoActividadRequest estadoActividad);
    EstadoActividadDomainEntity actualizarEstadoActividad(EstadoActividadRequest estadoActividad);
    void eliminarEstadoActividad(Long id);
    EstadoActividadDomainEntity obtenerEstadoActividadPorId(Long id);
    List<EstadoActividadDomainEntity> obtenerTodosLosEstadosActividad();
    List<EstadoActividadDomainEntity> listarEstadosActividad();
}