package com.zentry.sigea.module_usuarios.infrastructure.database.mappers;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

public class UsuarioMapper{
    
    public static UsuarioEntity toEntity(UsuarioDomainEntity usuarioDomainEntity){
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNombres(usuarioDomainEntity.getNombres());
        usuarioEntity.setApellidos(usuarioDomainEntity.getApellidos());
        usuarioEntity.setCorreo(usuarioDomainEntity.getCorreo());
        usuarioEntity.setPasswordHash(usuarioDomainEntity.getPasswordHash());
    
        return usuarioEntity;
    }

    public static UsuarioDomainEntity toDomain(UsuarioEntity usuarioEntity){
        UsuarioDomainEntity usuarioDomainEntity = new UsuarioDomainEntity();

        usuarioDomainEntity.setNombres(usuarioEntity.getNombres());
        usuarioDomainEntity.setApellidos(usuarioEntity.getApellidos());
        usuarioDomainEntity.setCorreo(usuarioEntity.getCorreo());
        usuarioDomainEntity.setPasswordHash(usuarioEntity.getPasswordHash());
        usuarioDomainEntity.setCreatedAt(usuarioEntity.getCreatedAt());
        usuarioDomainEntity.setUpdatedAt(usuarioEntity.getUpdatedAt());

        return usuarioDomainEntity;
    }
}
