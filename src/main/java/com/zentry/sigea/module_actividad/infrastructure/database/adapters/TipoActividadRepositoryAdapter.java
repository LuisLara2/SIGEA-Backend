package com.zentry.sigea.module_actividad.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.infrastructure.database.mappers.TipoActividadMapper;
import com.zentry.sigea.module_actividad.infrastructure.repository.TipoActividadJPARepository;

@Repository
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

    public boolean save(TipoActividadDomainEntity tipoActividadDomainEntity){
        try {
            tipoActividadJPARepository.save(
                TipoActividadMapper.toEntity(tipoActividadDomainEntity)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<TipoActividadDomainEntity> findById(String id){
        return tipoActividadJPARepository.findById(UUID.fromString(id))
            .map(ta -> TipoActividadMapper.toDomain(ta));
    }

    public void deleteById(String id){
        tipoActividadJPARepository.deleteById(UUID.fromString(id));
    }
}
