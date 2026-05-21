package com.zentry.sigea.module_actividad.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.EstadoActividadRequest;

public  interface IEstadoActividad {
    public String crearEstadoActividad(EstadoActividadRequest estadoActividad);
    public EstadoActividadDomainEntity actualizarEstadoActividad(EstadoActividadRequest estadoActividad);
    public void eliminarEstadoActividad(String id);
    public EstadoActividadDomainEntity obtenerEstadoActividadPorId(String id);
    public List<EstadoActividadDomainEntity> obtenerTodosLosEstadosActividad();
    public List<EstadoActividadDomainEntity> listarEstadosActividad();
}