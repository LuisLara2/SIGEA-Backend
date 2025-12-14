package com.zentry.sigea.module_usuarios.presentation.models.mappers;

import java.util.ArrayList;
import java.util.List;

import com.zentry.sigea.module_usuarios.core.entities.UsuarioDomainEntity;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.EnviarEstadisticasUsuariosResponseDTO;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.ObtenerUsuarioResponseDTO;
import com.zentry.sigea.module_usuarios.services.serviceDTO.EnviarEstadisticasUsuariosServiceDTO;

public class EnviarEstadisticasUsuariosMapper {
    public static EnviarEstadisticasUsuariosResponseDTO domainToResponse(EnviarEstadisticasUsuariosServiceDTO enviarEstadisticasUsuariosServiceDTO){
        EnviarEstadisticasUsuariosResponseDTO enviarEstadisticasUsuariosResponseDTO = new EnviarEstadisticasUsuariosResponseDTO();

        enviarEstadisticasUsuariosResponseDTO.setTotalRegisteredUsers(enviarEstadisticasUsuariosServiceDTO.getTotalRegisteredUsers());
        
        List<ObtenerUsuarioResponseDTO> listUsuarios = new ArrayList<>();
        for(UsuarioDomainEntity usuarioDomainEntity : enviarEstadisticasUsuariosServiceDTO.getListUsuarioDomainEntities()){
            ObtenerUsuarioResponseDTO obtenerUsuarioResponseDTO = ObtenerUsuarioMapper.domainToResponse(usuarioDomainEntity);

            listUsuarios.add(obtenerUsuarioResponseDTO);
        }
        enviarEstadisticasUsuariosResponseDTO.setListUsuarios(listUsuarios);

        enviarEstadisticasUsuariosResponseDTO.setTotalUsuariosOrganizador(enviarEstadisticasUsuariosServiceDTO.getTotalUsuariosOrganizador());
        List<ObtenerUsuarioResponseDTO> listUsuariosOrganizador = new ArrayList<>();
        for(UsuarioDomainEntity usuarioOrganizador : enviarEstadisticasUsuariosServiceDTO.getListUsuariosOrganizador()){
            ObtenerUsuarioResponseDTO obtenerUsuarioResponseDTO = ObtenerUsuarioMapper.domainToResponse(usuarioOrganizador);

            listUsuariosOrganizador.add(obtenerUsuarioResponseDTO);
        }
        enviarEstadisticasUsuariosResponseDTO.setListUsuariosOrganizador(listUsuariosOrganizador);

        enviarEstadisticasUsuariosResponseDTO.setTotalUsuariosParticipante(enviarEstadisticasUsuariosServiceDTO.getTotalUsuariosParticipante());
        List<ObtenerUsuarioResponseDTO> listUsuariosParticipante = new ArrayList<>();
        for(UsuarioDomainEntity usuarioParticipante : enviarEstadisticasUsuariosServiceDTO.getListUsuariosParticipante()){
            ObtenerUsuarioResponseDTO obtenerUsuarioResponseDTO = ObtenerUsuarioMapper.domainToResponse(usuarioParticipante);

            listUsuariosParticipante.add(obtenerUsuarioResponseDTO);
        }
        enviarEstadisticasUsuariosResponseDTO.setListUsuariosParticipante(listUsuariosParticipante);

        return enviarEstadisticasUsuariosResponseDTO;
    }
}
