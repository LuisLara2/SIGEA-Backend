package com.zentry.sigea.module_actividad.infrastructure.repository.estadoactividad_repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.core.repositories.EstadoActividadRepository;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.EstadoActividadEntity;
import com.zentry.sigea.module_actividad.infrastructure.database.mappers.EstadoActividadMapper;

@Repository
public class EstadoActividadImpl implements  EstadoActividadRepository {
    private final EstadoActividadJpaRepository jpaRepository;

    public EstadoActividadImpl(EstadoActividadJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }



    @Override
    public EstadoActividad findByCodigo(String codigo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCodigo'");
    }




    @Override
    public EstadoActividad save(EstadoActividad estadoActividad) {
        EstadoActividadEntity entity = EstadoActividadMapper.toEntity(estadoActividad);
        EstadoActividadEntity savedEntity = jpaRepository.save(entity);
        return EstadoActividadMapper.toDomain(savedEntity);
    }




    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }



    @Override
    public EstadoActividad findById(Long id) {
        Optional<EstadoActividadEntity> entity = jpaRepository.findById(id);
        return entity.map(EstadoActividadMapper::toDomain).orElse(null);
    }



    @Override
    public List<EstadoActividad> findAll() {
         List<EstadoActividadEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(EstadoActividadMapper::toDomain)
                .collect(Collectors.toList());
    }
}