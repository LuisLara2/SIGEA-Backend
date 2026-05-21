package com.zentry.sigea.module_actividad.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.infrastructure.database.mappers.EstadoActividadMapper;
import com.zentry.sigea.module_actividad.infrastructure.repository.EstadoActividadJPARepository;

@Repository
public class EstadoActividadRepositoryAdapter implements IEstadoActividadRepository {
    
    private final EstadoActividadJPARepository estadoActividadJPARepository;

    public EstadoActividadRepositoryAdapter(EstadoActividadJPARepository estadoActividadJPARepository){
        this.estadoActividadJPARepository = estadoActividadJPARepository;
    }

    public Optional<EstadoActividadDomainEntity> findById(String id){
        return estadoActividadJPARepository.findById(UUID.fromString(id))
            .map(ea -> EstadoActividadMapper.toDomain(ea));
    }

    public Optional<EstadoActividadDomainEntity> findByCodigo(String codigo){
        return estadoActividadJPARepository.findByCodigo(codigo)
            .map(ea -> EstadoActividadMapper.toDomain(ea));
    }

    public List<EstadoActividadDomainEntity> findAll(){
        return estadoActividadJPARepository.findAll()
            .stream()
            .map(EstadoActividadMapper::toDomain)
            .collect(Collectors.toList());
    }

    public boolean save(EstadoActividadDomainEntity estadoActividadDomainEntity){
        
        try {
            estadoActividadJPARepository.save(
                EstadoActividadMapper.toEntity(estadoActividadDomainEntity)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteById(String id){
        estadoActividadJPARepository.deleteById(UUID.fromString(id));
    }
}
