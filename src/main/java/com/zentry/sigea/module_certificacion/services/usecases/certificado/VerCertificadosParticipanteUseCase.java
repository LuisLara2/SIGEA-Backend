package com.zentry.sigea.module_certificacion.services.usecases.certificado;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_certificacion.core.entities.CertificadoDomainEntity;
import com.zentry.sigea.module_certificacion.core.entities.ValidacionDomainEntity;
import com.zentry.sigea.module_certificacion.core.repositories.ICertificadoRepository;
import com.zentry.sigea.module_certificacion.core.repositories.IValidacionRepository;
import com.zentry.sigea.module_certificacion.services.serviceDTO.VerCertificadosParticipanteResponseServiceDTO;
import com.zentry.sigea.module_inscripciones.core.repositories.IInscripcionRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IAsistenciaRepository;

@Component
public class VerCertificadosParticipanteUseCase {
    private final ICertificadoRepository certificadoRepository;
    private final IValidacionRepository validacionRepository;

    private final IInscripcionRepository inscripcionRepository;
    private final IAsistenciaRepository asistenciaRepository;

    public VerCertificadosParticipanteUseCase(
        ICertificadoRepository certificadoRepository , 
        IValidacionRepository validacionRepository , 

        IInscripcionRepository inscripcionRepository , 
        IAsistenciaRepository asistenciaRepository
    ){
        this.certificadoRepository = certificadoRepository;
        this.validacionRepository = validacionRepository;

        this.inscripcionRepository = inscripcionRepository;
        this.asistenciaRepository = asistenciaRepository;
    }

    public List<VerCertificadosParticipanteResponseServiceDTO> execute(String usuarioId){

        List<VerCertificadosParticipanteResponseServiceDTO> listVerCertificadosParticipanteServiceDTOs = new ArrayList<>();

        List<String> listActividadIds = inscripcionRepository.findByUsuarioId(usuarioId)
            .stream()
            .map(i -> i.getActividadId())
            .collect(Collectors.toList());

        for(String actividadId : listActividadIds){
            String idInscripcion = inscripcionRepository.findIdByUsuarioIdAndActividadId(usuarioId, actividadId);

            List<String> listAsistenciaIds = asistenciaRepository.findIdsByInscripcionId(idInscripcion);
        
            CertificadoDomainEntity certificadoDomainEntity = certificadoRepository.findByListActividadIds(listAsistenciaIds).orElse(null);
        
            if(certificadoDomainEntity == null){
                continue;
            }

            VerCertificadosParticipanteResponseServiceDTO verCertificadosParticipanteServiceDTO = new VerCertificadosParticipanteResponseServiceDTO();

            ValidacionDomainEntity validacionDomainEntity = validacionRepository.findByCertificadoId(
                certificadoDomainEntity.getId()
            ).orElse(null);

            verCertificadosParticipanteServiceDTO.setCertificadoId(certificadoDomainEntity.getId());
            verCertificadosParticipanteServiceDTO.setFechaEmision(certificadoDomainEntity.getFechaEmision());
            verCertificadosParticipanteServiceDTO.setEstadoId(certificadoDomainEntity.getEstadoCertificadoDomainEntity().getId());
            verCertificadosParticipanteServiceDTO.setEstadoCertificadoCodigo(certificadoDomainEntity.getEstadoCertificadoDomainEntity().getCodigo());
            verCertificadosParticipanteServiceDTO.setUrlPdf(certificadoDomainEntity.getUrlPdf());

            verCertificadosParticipanteServiceDTO.setFechaValidacion(validacionDomainEntity.getFechaValidacion());
            verCertificadosParticipanteServiceDTO.setResultado(validacionDomainEntity.getResultado());
            
            listVerCertificadosParticipanteServiceDTOs.add(verCertificadosParticipanteServiceDTO);
        }

        return listVerCertificadosParticipanteServiceDTOs;
    }
}
