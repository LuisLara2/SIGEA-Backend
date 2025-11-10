package com.zentry.sigea.module_usuarios.services.usecases;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_usuarios.core.entities.InscripcionDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IInscripcionRepository;
import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarInscripcionRequestDTO;

@Component
public class RegistrarInscripcionUseCase {
    
    private final IInscripcionRepository inscripcionRepository;

    public RegistrarInscripcionUseCase(IInscripcionRepository inscripcionRepository){
        this.inscripcionRepository = inscripcionRepository;
    }

    public void execute(RegistrarInscripcionRequestDTO registrarInscripcionRequestDTO){
        InscripcionDomainEntity inscripcionDomainEntity = InscripcionDomainEntity.create(
            registrarInscripcionRequestDTO.getUsuarioId() , 
            registrarInscripcionRequestDTO.getActividadId() , 
            registrarInscripcionRequestDTO.getEstadoActividadId()
        );
        
        inscripcionRepository.save(inscripcionDomainEntity);
    }

}
