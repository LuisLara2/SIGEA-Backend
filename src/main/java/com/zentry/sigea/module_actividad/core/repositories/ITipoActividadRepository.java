package com.zentry.sigea.module_actividad.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;

public interface ITipoActividadRepository {
    public List<TipoActividadDomainEntity> findAll();
    public boolean save(TipoActividadDomainEntity tipoActividadDomainEntity);
    public Optional<TipoActividadDomainEntity> findById(String id);
    public void deleteById(String id);
    // List<TipoActividad> findByNombre(String nombre);
    // List<TipoActividad> findByDescripcion(String descripcion);

}
