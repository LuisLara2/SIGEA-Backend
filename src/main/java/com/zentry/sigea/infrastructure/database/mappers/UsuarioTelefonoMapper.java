package com.zentry.sigea.infrastructure.database.mappers;

import com.zentry.sigea.core.entities.UsuarioTelefonoDomainEntity;
import com.zentry.sigea.infrastructure.database.entities.UsuarioTelefonoEntity;

public class UsuarioTelefonoMapper {
    public static UsuarioTelefonoEntity toEntity(UsuarioTelefonoDomainEntity usuarioTelefonoDomainEntity){
        UsuarioTelefonoEntity usuarioTelefonoEntity = new UsuarioTelefonoEntity();

        usuarioTelefonoEntity.setTelefono(usuarioTelefonoDomainEntity.getTelefono());
        usuarioTelefonoEntity.setTipo(usuarioTelefonoDomainEntity.getTipo());

        return usuarioTelefonoEntity;
    }

    public static UsuarioTelefonoDomainEntity toDomain(UsuarioTelefonoEntity usuarioTelefonoEntity){
        UsuarioTelefonoDomainEntity usuarioTelefonoDomainEntity = new UsuarioTelefonoDomainEntity();

        usuarioTelefonoEntity.setTelefono(usuarioTelefonoEntity.getTelefono());
        usuarioTelefonoEntity.setTipo(usuarioTelefonoEntity.getTipo());

        return usuarioTelefonoDomainEntity;
    }
}
