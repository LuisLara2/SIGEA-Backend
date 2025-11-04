package com.zentry.sigea.module_actividad.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.infrastructure.database.mappers.TipoActividadMapper;
import com.zentry.sigea.module_actividad.infrastructure.repository.TipoActividadJPARepository;

public class TipoActividadRepositoryAdapter implements ITipoActividadRepository {
    
    private final TipoActividadJPARepository tipoActividadJPARepository;

    public TipoActividadRepositoryAdapter(TipoActividadJPARepository tipoActividadJPARepository){
        this.tipoActividadJPARepository = tipoActividadJPARepository;
    }

    public List<TipoActividadDomainEntity> findAll(){
        return tipoActividadJPARepository.findAll()
            .stream()
            .map(TipoActividadMapper::toDomain)
            .collect(Collectors.toList());
    }

    public void save(TipoActividadDomainEntity tipoActividadDomainEntity){
        tipoActividadJPARepository.save(
            TipoActividadMapper.toEntity(tipoActividadDomainEntity)
        );
    }

    public Optional<TipoActividadDomainEntity> findById(String id){
        return tipoActividadJPARepository.findById(UUID.fromString(id))
            .map(ta -> TipoActividadMapper.toDomain(ta));
    }

    public void deleteById(String id){
        tipoActividadJPARepository.deleteById(UUID.fromString(id));
    }
}
