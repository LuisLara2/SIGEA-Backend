package com.zentry.sigea.module_certificacion.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_certificacion.services.serviceDTO.VerCertificadosParticipanteResponseServiceDTO;
import com.zentry.sigea.module_certificacion.services.usecases.certificado.VerCertificadosParticipanteUseCase;

@Service
public class CertificadoQueryService {
    
    private final VerCertificadosParticipanteUseCase verCertificadosParticipanteUseCase;

    public CertificadoQueryService(
        VerCertificadosParticipanteUseCase verCertificadosParticipanteUseCase
    ){
        this.verCertificadosParticipanteUseCase = verCertificadosParticipanteUseCase;
    }

    public List<VerCertificadosParticipanteResponseServiceDTO> verCertificadosParticipante(String usuarioId){
        return verCertificadosParticipanteUseCase.execute(usuarioId);
    }
}
