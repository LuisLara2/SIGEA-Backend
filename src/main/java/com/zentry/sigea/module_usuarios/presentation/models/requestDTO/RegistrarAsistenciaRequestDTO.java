package com.zentry.sigea.module_usuarios.presentation.models.requestDTO;

import jakarta.validation.constraints.NotNull;

public class RegistrarAsistenciaRequestDTO {

    @NotNull(message = "Debe proporcionar una sesion.")
    private String sesionId;

    @NotNull(message = "Debe proporcionar una inscripcion.")
    private String inscripcionId;

    private Boolean presente;

    public String getSesionId() {
        return sesionId;
    }

    public String getInscripcionId() {
        return inscripcionId;
    }

    public Boolean getPresente() {
        return presente;
    }
}
