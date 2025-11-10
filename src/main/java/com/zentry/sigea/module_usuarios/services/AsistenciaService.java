package com.zentry.sigea.module_usuarios.services;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_usuarios.core.entities.AsistenciaDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IAsistenciaRepository;
import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarAsistenciaRequestDTO;

@Service
public class AsistenciaService {
    
    private final IAsistenciaRepository asistenciaRepository;

    public AsistenciaService(IAsistenciaRepository asistenciaRepository){
        this.asistenciaRepository = asistenciaRepository;
    }

    public void registrarAsistencia(RegistrarAsistenciaRequestDTO registrarAsistenciaRequestDTO){

        AsistenciaDomainEntity asistenciaDomainEntity = AsistenciaDomainEntity.create(
            registrarAsistenciaRequestDTO.getSesionId() , 
            registrarAsistenciaRequestDTO.getInscripcionId() , 
            registrarAsistenciaRequestDTO.getPresente()
        );

        asistenciaRepository.save(asistenciaDomainEntity);
    }

}
