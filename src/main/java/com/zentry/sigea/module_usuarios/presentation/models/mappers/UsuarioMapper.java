package com.zentry.sigea.module_usuarios.presentation.models.mappers;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarUsuarioRequestDTO;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.UsuarioResponseDTO;

public class UsuarioMapper {
    // El mapeo tiene en cuenta este flujo
    // Cuando el usuario hace una solicitud:    request -> domain
    // Cuando se responde a la colucitud:       domain -> response
    
    public static UsuarioDomainEntity requestToDomain(RegistrarUsuarioRequestDTO usuarioRequestDTO , String passwordHash){
        UsuarioDomainEntity usuarioDomainEntity = new UsuarioDomainEntity();
        
        usuarioDomainEntity.setNombres(usuarioRequestDTO.getNombres());
        usuarioDomainEntity.setApellidos(usuarioRequestDTO.getNombres());
        usuarioDomainEntity.setPasswordHash(
            passwordHash
        );
        

        return usuarioDomainEntity;
    }

    public static UsuarioResponseDTO domainToResponse(UsuarioDomainEntity usuarioDomainEntity){
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();
        
        return usuarioResponseDTO;
    }
}
