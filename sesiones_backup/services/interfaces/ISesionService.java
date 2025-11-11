package com.zentry.sigea.modules.sesiones.services.interfaces;

import com.zentry.sigea.modules.sesiones.presentation.models.SesionDTO;
import com.zentry.sigea.modules.sesiones.presentation.models.SesionRequest;

import java.util.List;

public interface ISesionService {
    SesionDTO crear(SesionRequest request);
    List<SesionDTO> listar();
   
}
