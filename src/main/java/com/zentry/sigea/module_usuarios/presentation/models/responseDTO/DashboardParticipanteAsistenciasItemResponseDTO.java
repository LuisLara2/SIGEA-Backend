package com.zentry.sigea.module_usuarios.presentation.models.responseDTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardParticipanteAsistenciasItemResponseDTO {
    private String inscripcionId;
    private LocalDate fechaInscripcion;

    private String nombresParticipante;
    private String correoParticipante;
    
    private Boolean presente;
}
