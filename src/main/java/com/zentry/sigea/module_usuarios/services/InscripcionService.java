package com.zentry.sigea.module_usuarios.services;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarInscripcionRequestDTO;
import com.zentry.sigea.module_usuarios.services.usecases.RegistrarInscripcionUseCase;

@Service
public class InscripcionService {
    
    private final RegistrarInscripcionUseCase registrarInscripcionUseCase;

    public InscripcionService(
        RegistrarInscripcionUseCase registrarInscripcionUseCase
    ){
        this.registrarInscripcionUseCase = registrarInscripcionUseCase;
    }

    public void registrarInscripcion(RegistrarInscripcionRequestDTO registrarInscripcionRequestDTO){
        registrarInscripcionUseCase.execute(registrarInscripcionRequestDTO);
    }
}