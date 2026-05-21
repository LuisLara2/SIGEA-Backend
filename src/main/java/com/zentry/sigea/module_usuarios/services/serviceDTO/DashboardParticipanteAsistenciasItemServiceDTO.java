package com.zentry.sigea.module_usuarios.services.serviceDTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardParticipanteAsistenciasItemServiceDTO {
    private String inscripcionId;
    private LocalDate fechaInscripcion;

    private String nombresParticipante;
    private String correoParticipante;
    
    private Boolean presente;
}