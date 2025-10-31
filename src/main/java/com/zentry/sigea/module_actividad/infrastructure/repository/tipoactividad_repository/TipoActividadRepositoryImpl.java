package com.zentry.sigea.module_actividad.infrastructure.repository.tipoactividad_repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.infrastructure.database.entities.TipoActividadEntity;
import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.core.repositories.TipoActividadRepository;
import com.zentry.sigea.module_actividad.infrastructure.database.mappers.TipoActividadMapper;

@Repository
public class TipoActividadRepositoryImpl implements TipoActividadRepository {
    private final TipoActividadJpaRepository jpaRepository;
    
    public TipoActividadRepositoryImpl(TipoActividadJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<TipoActividad> findAll() {
        List<TipoActividadEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(TipoActividadMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public TipoActividad save(TipoActividad tipoActividad) {
        TipoActividadEntity entity = TipoActividadMapper.toEntity(tipoActividad);
        TipoActividadEntity savedEntity = jpaRepository.save(entity);
        return TipoActividadMapper.toDomain(savedEntity);
    }

    @Override
    public TipoActividad findById(Long id) {
        Optional<TipoActividadEntity> entity = jpaRepository.findById(id);
        return entity.map(TipoActividadMapper::toDomain).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
