package com.zentry.sigea.module_sesiones.services.interfaces;

import com.zentry.sigea.module_sesiones.presentacion.models.CrearSesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionResponse;

import java.util.List;
import java.util.UUID;

/**
 * Interfaz del servicio de sesiones
 */
public interface ISesionService {
    
    SesionResponse crearSesion(CrearSesionRequest request);
    
    List<SesionResponse> listarSesiones();
    
    List<SesionResponse> listarSesionesPorActividad(UUID actividadId);
    
    SesionResponse obtenerSesionPorId(Long id);
    
    SesionResponse actualizarSesion(Long id, SesionRequest request);
    
    void eliminarSesion(Long id);
}
