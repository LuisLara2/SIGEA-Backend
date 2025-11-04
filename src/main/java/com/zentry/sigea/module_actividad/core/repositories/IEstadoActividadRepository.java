package com.zentry.sigea.module_actividad.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;

public interface IEstadoActividadRepository {
    public Optional<EstadoActividadDomainEntity> findById(String id);
    public Optional<EstadoActividadDomainEntity> findByCodigo(String codigo);
    public List<EstadoActividadDomainEntity> findAll();
    public void save(EstadoActividadDomainEntity estadoActividadDomainEntity);
    public void deleteById(String id);
}
