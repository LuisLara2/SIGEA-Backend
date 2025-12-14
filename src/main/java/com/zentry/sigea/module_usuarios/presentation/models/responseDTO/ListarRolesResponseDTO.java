package com.zentry.sigea.module_usuarios.presentation.models.responseDTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarRolesResponseDTO {
    private String id;
    private String nombreRol;
    private String descripcion;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
