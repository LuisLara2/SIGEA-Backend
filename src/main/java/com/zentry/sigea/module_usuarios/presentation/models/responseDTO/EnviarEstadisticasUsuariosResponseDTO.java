package com.zentry.sigea.module_usuarios.presentation.models.responseDTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnviarEstadisticasUsuariosResponseDTO {
    private Integer totalRegisteredUsers;
    private List<ObtenerUsuarioResponseDTO> listUsuarios;

    private Integer totalUsuariosOrganizador;
    private List<ObtenerUsuarioResponseDTO> listUsuariosOrganizador;

    private Integer totalUsuariosParticipante;
    private List<ObtenerUsuarioResponseDTO> listUsuariosParticipante;
}
