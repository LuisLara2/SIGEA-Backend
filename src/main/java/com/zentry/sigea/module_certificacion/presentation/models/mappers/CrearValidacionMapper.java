package com.zentry.sigea.module_certificacion.presentation.models.mappers;

import com.zentry.sigea.module_certificacion.presentation.models.requestDTO.CrearValidacionRequestDTO;
import com.zentry.sigea.module_certificacion.services.serviceDTO.CrearValidacionRequestServiceDTO;

public class CrearValidacionMapper {
    public static CrearValidacionRequestServiceDTO requestToService(CrearValidacionRequestDTO crearValidacionRequestDTO){
        CrearValidacionRequestServiceDTO crearValidacionRequestServiceDTO = new CrearValidacionRequestServiceDTO();

        crearValidacionRequestServiceDTO.setCertificadoId(crearValidacionRequestDTO.getCertificadoId());
        crearValidacionRequestServiceDTO.setTipoValidadorId(crearValidacionRequestDTO.getTipoValidadorId());
        crearValidacionRequestServiceDTO.setResultado(crearValidacionRequestDTO.getResultado());
        crearValidacionRequestServiceDTO.setDetalle(crearValidacionRequestDTO.getDetalle());

        return crearValidacionRequestServiceDTO;
    }
}
