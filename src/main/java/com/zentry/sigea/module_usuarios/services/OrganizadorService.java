package com.zentry.sigea.module_usuarios.services;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_usuarios.services.serviceDTO.RegistrarAsistenciaServiceDTO;
import com.zentry.sigea.module_usuarios.services.usecases.organizador.RegistrarAsistenciaUseCase;

@Service
public class OrganizadorService {
    
    private final RegistrarAsistenciaUseCase registrarAsistenciaUseCase;

    public OrganizadorService(
        RegistrarAsistenciaUseCase registrarAsistenciaUseCase
    ){
        this.registrarAsistenciaUseCase = registrarAsistenciaUseCase;
    }

    public String registrarAsistencia(
        RegistrarAsistenciaServiceDTO registrarAsistenciaServiceDTO
    ){
        return registrarAsistenciaUseCase.execute(registrarAsistenciaServiceDTO);
    }
}
