package com.zentry.sigea.module_actividad.core.repositories;

import java.util.List;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;

public interface TipoActividadRepository {

    List<TipoActividad> findAll();
    TipoActividad save(TipoActividad tipoActividad);
    TipoActividad findById(Long id);
    void deleteById(Long id);
    List<TipoActividad> findByNombre(String nombre);
    List<TipoActividad> findByDescripcion(String descripcion);

}
