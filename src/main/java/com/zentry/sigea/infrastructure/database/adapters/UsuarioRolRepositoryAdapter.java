package com.zentry.sigea.infrastructure.database.adapters;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.core.entities.RolDomainEntity;
import com.zentry.sigea.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.infrastructure.database.entities.UsuarioRolEntity;
import com.zentry.sigea.infrastructure.database.mappers.RolMapper;
import com.zentry.sigea.infrastructure.repositories.UsuarioRolJPARepository;

@Repository
public class UsuarioRolRepositoryAdapter implements IUsuarioRolRepository {
    
    private final UsuarioRolJPARepository usuarioRolJPARepository;

    public UsuarioRolRepositoryAdapter(UsuarioRolJPARepository usuarioRolJPARepository){
        this.usuarioRolJPARepository = usuarioRolJPARepository;
    }

    public List<RolDomainEntity> findRolesByUsuarioId(String usuarioId){
        List<UsuarioRolEntity> usuarioRolEntity = usuarioRolJPARepository.findById_UsuarioId(UUID.fromString(usuarioId));
        
        return usuarioRolEntity.stream()
            .map(ur -> RolMapper.toDomain(ur.getRol()))
            .collect(Collectors.toList());
    }

    public String findAsingandoEnByUsuarioIdAndRolId(String usuarioId , String rolId){
        return usuarioRolJPARepository.findAsignadoEnById_UsuarioIdAndId_RolId(
            UUID.fromString(usuarioId) , 
            UUID.fromString(rolId)
        );
    }
}
