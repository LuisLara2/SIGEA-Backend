package com.zentry.sigea.module_certificacion.services.serviceDTO;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerCertificadosParticipanteResponseServiceDTO {
    private String certificadoId;
    private LocalDate fechaEmision;
    private String estadoId;
    private String estadoCertificadoCodigo;
    private String urlPdf;

    private LocalDate fechaValidacion;
    private String resultado;
}
