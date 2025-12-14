package com.zentry.sigea.module_usuarios.presentation.models.mappers;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.ObtenerUsuarioResponseDTO;

public class ObtenerUsuarioMapper {
    public static ObtenerUsuarioResponseDTO domainToResponse(
        UsuarioDomainEntity usuarioDomainEntity
    ){
        ObtenerUsuarioResponseDTO obtenerUsuarioResponseDTO = new ObtenerUsuarioResponseDTO();

        obtenerUsuarioResponseDTO.setId(usuarioDomainEntity.getId());
        obtenerUsuarioResponseDTO.setNombres(usuarioDomainEntity.getNombres());
        obtenerUsuarioResponseDTO.setApellidos(usuarioDomainEntity.getApellidos());
        obtenerUsuarioResponseDTO.setCorreo(usuarioDomainEntity.getCorreo());
        obtenerUsuarioResponseDTO.setDni(usuarioDomainEntity.getDni());
        obtenerUsuarioResponseDTO.setCorreoVerificado(usuarioDomainEntity.getCorreoVerificado());
        obtenerUsuarioResponseDTO.setCreatedAt(usuarioDomainEntity.getCreatedAt());
        obtenerUsuarioResponseDTO.setUpdatedAt(usuarioDomainEntity.getUpdatedAt());
        obtenerUsuarioResponseDTO.setTelefono(usuarioDomainEntity.getTelefono());
        obtenerUsuarioResponseDTO.setExtensionTelefonica(usuarioDomainEntity.getExtensionTelefonica());

        return obtenerUsuarioResponseDTO;
    }
}
