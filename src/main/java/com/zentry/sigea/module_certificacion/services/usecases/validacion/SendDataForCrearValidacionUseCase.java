package com.zentry.sigea.module_certificacion.services.usecases.validacion;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IActividadRespository;
import com.zentry.sigea.module_certificacion.core.repositories.ICertificadoRepository;
import com.zentry.sigea.module_inscripciones.core.repositories.IInscripcionRepository;
import com.zentry.sigea.module_usuarios.core.repositories.IAsistenciaRepository;

@Component
public class SendDataForCrearValidacionUseCase {
    private final ICertificadoRepository certificadoRepository;
    private final IAsistenciaRepository asistenciaRepository;
    private final IInscripcionRepository inscripcionRepository;
    private final IActividadRespository actividadRespository;

    public SendDataForCrearValidacionUseCase(
        ICertificadoRepository certificadoRepository,
        IAsistenciaRepository asistenciaRepository,
        IInscripcionRepository inscripcionRepository,
        IActividadRespository actividadRespository
    ){
        this.certificadoRepository = certificadoRepository;
        this.asistenciaRepository = asistenciaRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.actividadRespository = actividadRespository;
    }

    public String sendDataForCrearValidacion(){
        List<String> listActividadIds = actividadRespository.findAllIds();

        List<String> listInscripcionIds = inscripcionRepository.findIdByListActividadIds(listActividadIds);
        

    }
}
