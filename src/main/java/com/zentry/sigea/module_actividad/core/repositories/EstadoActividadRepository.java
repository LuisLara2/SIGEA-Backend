package com.zentry.sigea.module_actividad.core.repositories;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;

public interface EstadoActividadRepository {
    EstadoActividad findById(Long id);
    EstadoActividad findByCodigo(String codigo);
    List<EstadoActividad> findAll();
    EstadoActividad save(EstadoActividad estadoActividad);
    void deleteById(Long id);

}
