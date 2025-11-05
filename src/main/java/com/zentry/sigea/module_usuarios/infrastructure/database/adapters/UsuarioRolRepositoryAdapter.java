package com.zentry.sigea.module_usuarios.infrastructure.database.adapters;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IUsuarioRolRepository;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioRolEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.mappers.RolMapper;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.UsuarioRolJPARepository;

@Repository
public class UsuarioRolRepositoryAdapter implements IUsuarioRolRepository {
    
    private final UsuarioRolJPARepository usuarioRolJPARepository;

    public UsuarioRolRepositoryAdapter(UsuarioRolJPARepository usuarioRolJPARepository){
        this.usuarioRolJPARepository = usuarioRolJPARepository;
    }

    public List<RolDomainEntity> findRolesByUsuarioId(String usuarioId){
        List<UsuarioRolEntity> usuarioRolEntity = usuarioRolJPARepository.findById_IdUsuario(UUID.fromString(usuarioId));
        
        return usuarioRolEntity.stream()
            .map(ur -> RolMapper.toDomain(ur.getRol()))
            .collect(Collectors.toList());
    }

    public LocalDateTime findAsingandoEnByUsuarioIdAndRolId(String usuarioId , String rolId){
        return usuarioRolJPARepository.findAsignadoEnById_IdUsuarioAndId_IdRol(
            UUID.fromString(usuarioId) , 
            UUID.fromString(rolId)
        );
    }
}
