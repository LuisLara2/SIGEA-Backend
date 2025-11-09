package com.zentry.sigea.module_inscripciones.services.interfaces;

import java.util.List;

import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.EstadoInscripcionRequest;
import com.zentry.sigea.module_inscripciones.presentation.models.responseDTO.EstadoInscripcionResponse;

/**
 * Interfaz del servicio de estados de inscripción
 */
public interface IEstadoInscripcion {
    String crearEstadoInscripcion(EstadoInscripcionRequest request);
    List<EstadoInscripcionResponse> listarEstadosInscripcion();
    EstadoInscripcionResponse obtenerEstadoInscripcionPorId(String id);
    EstadoInscripcionResponse obtenerEstadoInscripcionPorCodigo(String codigo);
    void eliminarEstadoInscripcion(String id);
}

