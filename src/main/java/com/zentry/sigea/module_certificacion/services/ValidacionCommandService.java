package com.zentry.sigea.module_certificacion.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_certificacion.services.serviceDTO.CrearValidacionRequestServiceDTO;
import com.zentry.sigea.module_certificacion.services.usecases.validacion.CrearValidacionUseCase;

@Service
public class ValidacionCommandService {
    
    private final CrearValidacionUseCase crearValidacionUseCase;

    public ValidacionCommandService(
        CrearValidacionUseCase crearValidacionUseCase
    ){
        this.crearValidacionUseCase = crearValidacionUseCase;
    }

    public String crearValidacion(List<CrearValidacionRequestServiceDTO> listCrearValidacionRequestServiceDTOs){
        return crearValidacionUseCase.execute(listCrearValidacionRequestServiceDTOs);
    }

}
