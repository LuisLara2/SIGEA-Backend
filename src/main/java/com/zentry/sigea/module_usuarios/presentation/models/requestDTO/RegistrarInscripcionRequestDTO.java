package com.zentry.sigea.module_usuarios.presentation.models.requestDTO;

import jakarta.validation.constraints.NotNull;

public class RegistrarInscripcionRequestDTO {

    @NotNull(message = "Debe proporcionar un usuario para el registro.")
    private String usuarioId;

    @NotNull(message = "Debe proporcionar una actividad para el registro.")
    private String actividadId;

    @NotNull(message = "Debe proporcionar el estado de la actividad a la que se va a registrar.")
    private String estadoActividadId;

    public String getUsuarioId() {
        return usuarioId;
    }
    public String getActividadId() {
        return actividadId;
    }
    public String getEstadoActividadId() {
        return estadoActividadId;
    }
}
