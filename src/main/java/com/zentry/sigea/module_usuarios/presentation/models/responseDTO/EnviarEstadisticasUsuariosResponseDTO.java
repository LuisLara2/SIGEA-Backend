package com.zentry.sigea.module_usuarios.presentation.models.responseDTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnviarEstadisticasUsuariosResponseDTO {
    private Integer totalRegisteredUsers;
    private List<EnviarEstadisticasUsuariosItemResponseDTO> listUsuarios;

    private Integer totalUsuariosOrganizador;
    private List<EnviarEstadisticasUsuariosItemResponseDTO> listUsuariosOrganizador;

    private Integer totalUsuariosParticipante;
    private List<EnviarEstadisticasUsuariosItemResponseDTO> listUsuariosParticipante;
}
