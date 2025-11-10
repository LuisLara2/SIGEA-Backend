package com.zentry.sigea.module_sesiones.services.interfaces;

import com.zentry.sigea.module_sesiones.presentacion.models.CrearSesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionResponse;

import java.util.List;

/**
 * Interfaz del servicio de sesiones
 */
public interface ISesionService {
    
    SesionResponse crearSesion(CrearSesionRequest request);
    
    List<SesionResponse> listarSesiones();
    
    List<SesionResponse> listarSesionesPorActividad(String actividadId);
    
    SesionResponse obtenerSesionPorId(String id);
    
    SesionResponse actualizarSesion(String id, SesionRequest request);
    
    void eliminarSesion(String id);
}
