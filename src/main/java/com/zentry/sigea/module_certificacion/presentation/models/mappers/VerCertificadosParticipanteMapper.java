package com.zentry.sigea.module_certificacion.presentation.models.mappers;

import com.zentry.sigea.module_certificacion.presentation.models.responseDTO.VerCertificadosParticipanteResponseDTO;
import com.zentry.sigea.module_certificacion.services.serviceDTO.VerCertificadosParticipanteResponseServiceDTO;

public class VerCertificadosParticipanteMapper {
    public static VerCertificadosParticipanteResponseDTO serviceToResponse(VerCertificadosParticipanteResponseServiceDTO verCertificadosParticipanteResponseServiceDTO){
        VerCertificadosParticipanteResponseDTO verCertificadosParticipanteResponseDTO = new VerCertificadosParticipanteResponseDTO();

        verCertificadosParticipanteResponseDTO.setCertificadoId(verCertificadosParticipanteResponseServiceDTO.getCertificadoId());
        verCertificadosParticipanteResponseDTO.setFechaEmision(verCertificadosParticipanteResponseServiceDTO.getFechaEmision());
        verCertificadosParticipanteResponseDTO.setEstadoId(verCertificadosParticipanteResponseServiceDTO.getEstadoId());
        verCertificadosParticipanteResponseDTO.setEstadoCertificadoCodigo(verCertificadosParticipanteResponseServiceDTO.getEstadoCertificadoCodigo());
        verCertificadosParticipanteResponseDTO.setUrlPdf(verCertificadosParticipanteResponseServiceDTO.getUrlPdf());
        verCertificadosParticipanteResponseDTO.setFechaValidacion(verCertificadosParticipanteResponseServiceDTO.getFechaValidacion());
        verCertificadosParticipanteResponseDTO.setResultado(verCertificadosParticipanteResponseServiceDTO.getResultado());

        return verCertificadosParticipanteResponseDTO;
    }
}
