package com.zentry.sigea.module_usuarios.infrastructure.database.adapters;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_usuarios.core.entities.EstadoInscripcionDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IEstadoInscripcionRepository;
import com.zentry.sigea.module_usuarios.infrastructure.database.mappers.EstadoInscripcionMapper;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.EstadoInscripcionJPARepository;

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

    public boolean deleteById(String id){
        try {
            estadoInscripcionJPARepository.deleteById(UUID.fromString(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
