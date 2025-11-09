package com.zentry.sigea.module_inscripciones.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_inscripciones.core.entities.EstadoInscripcionDomainEntity;
import com.zentry.sigea.module_inscripciones.core.repositories.IEstadoInscripcionRepository;
import com.zentry.sigea.module_inscripciones.infrastructure.database.mappers.EstadoInscripcionMapper;
import com.zentry.sigea.module_inscripciones.infrastructure.repository.EstadoInscripcionJPARepository;

@Repository
public class EstadoInscripcionRepositoryAdapter implements IEstadoInscripcionRepository {
    
    private final EstadoInscripcionJPARepository estadoInscripcionJPARepository;

    public EstadoInscripcionRepositoryAdapter(EstadoInscripcionJPARepository estadoInscripcionJPARepository){
        this.estadoInscripcionJPARepository = estadoInscripcionJPARepository;
    }

    public Optional<EstadoInscripcionDomainEntity> findById(String id){
        return estadoInscripcionJPARepository.findById(UUID.fromString(id))
            .map(ei -> EstadoInscripcionMapper.toDomain(ei));
    }

    public Optional<EstadoInscripcionDomainEntity> findByCodigo(String codigo){
        return estadoInscripcionJPARepository.findByCodigo(codigo)
            .map(ei -> EstadoInscripcionMapper.toDomain(ei));
    }

    public List<EstadoInscripcionDomainEntity> findAll(){
        return estadoInscripcionJPARepository.findAll()
            .stream()
            .map(EstadoInscripcionMapper::toDomain)
            .collect(Collectors.toList());
    }

    public boolean save(EstadoInscripcionDomainEntity estadoInscripcionDomainEntity){
        try {
            estadoInscripcionJPARepository.save(
                EstadoInscripcionMapper.toEntity(estadoInscripcionDomainEntity)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteById(String id){
        estadoInscripcionJPARepository.deleteById(UUID.fromString(id));
    }
}

