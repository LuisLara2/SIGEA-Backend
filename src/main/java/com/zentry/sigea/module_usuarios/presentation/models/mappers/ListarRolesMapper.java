package com.zentry.sigea.module_usuarios.presentation.models.mappers;

import com.zentry.sigea.module_usuarios.core.entities.RolDomainEntity;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.ListarRolesResponseDTO;

public class ListarRolesMapper {
    public static ListarRolesResponseDTO domainToResponse(RolDomainEntity rolDomainEntity){
        ListarRolesResponseDTO listarRolesResponseDTO = new ListarRolesResponseDTO();

        listarRolesResponseDTO.setId(rolDomainEntity.getId());
        listarRolesResponseDTO.setNombreRol(rolDomainEntity.getNombreRol());
        listarRolesResponseDTO.setDescripcion(rolDomainEntity.getDescripcion());
        listarRolesResponseDTO.setCreatedAt(rolDomainEntity.getCreateAt());
        listarRolesResponseDTO.setUpdatedAt(rolDomainEntity.getUpdateAt());

        return listarRolesResponseDTO;
    }
}
