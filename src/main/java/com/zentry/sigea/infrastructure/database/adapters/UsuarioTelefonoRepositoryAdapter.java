package com.zentry.sigea.infrastructure.database.adapters;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.zentry.sigea.core.entities.UsuarioTelefonoDomainEntity;
import com.zentry.sigea.core.repositories.IUsuarioTelefonoRepository;
import com.zentry.sigea.infrastructure.database.mappers.UsuarioTelefonoMapper;
import com.zentry.sigea.infrastructure.repositories.UsuarioTelefonoJPARepository;

public class UsuarioTelefonoRepositoryAdapter implements IUsuarioTelefonoRepository {
    
    private final UsuarioTelefonoJPARepository usuarioTelefonoJPARepository;

    public UsuarioTelefonoRepositoryAdapter(UsuarioTelefonoJPARepository usuarioTelefonoJPARepository){
        this.usuarioTelefonoJPARepository = usuarioTelefonoJPARepository;
    }

    public List<UsuarioTelefonoDomainEntity> findAllByUsuarioId(String usuarioId){
        return usuarioTelefonoJPARepository.findAllByUsuarioId(UUID.fromString(usuarioId))
            .stream()
            .map(UsuarioTelefonoMapper::toDomain)
            .collect(Collectors.toList());
    }

    public List<UsuarioTelefonoDomainEntity> findAllById(String id){
        return usuarioTelefonoJPARepository.findAllById(UUID.fromString(id))
            .stream()
            .map(UsuarioTelefonoMapper::toDomain)
            .collect(Collectors.toList());
    }
}
