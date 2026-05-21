package com.zentry.sigea.module_inscripciones.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_inscripciones.core.repositories.IEstadoInscripcionRepository;
import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.EstadoInscripcionRequest;
import com.zentry.sigea.module_inscripciones.presentation.models.responseDTO.EstadoInscripcionResponse;
import com.zentry.sigea.module_inscripciones.services.interfaces.IEstadoInscripcion;
import com.zentry.sigea.module_inscripciones.services.usecases.estado_inscripcion.CrearEstadoInscripcionUseCase;
import com.zentry.sigea.module_inscripciones.services.usecases.estado_inscripcion.EliminarEstadoInscripcionUseCase;

/**
 * Servicio de aplicación que orquesta casos de uso de estados de inscripción
 */
@Service
public class EstadoInscripcionService implements IEstadoInscripcion {

    private final IEstadoInscripcionRepository estadoInscripcionRepository;
    private final CrearEstadoInscripcionUseCase crearEstadoInscripcionUseCase;
    private final EliminarEstadoInscripcionUseCase eliminarEstadoInscripcionUseCase;

    public EstadoInscripcionService(
        IEstadoInscripcionRepository estadoInscripcionRepository,
        CrearEstadoInscripcionUseCase crearEstadoInscripcionUseCase,
        EliminarEstadoInscripcionUseCase eliminarEstadoInscripcionUseCase
    ) {
        this.estadoInscripcionRepository = estadoInscripcionRepository;
        this.crearEstadoInscripcionUseCase = crearEstadoInscripcionUseCase;
        this.eliminarEstadoInscripcionUseCase = eliminarEstadoInscripcionUseCase;
    }

    @Override
    public String crearEstadoInscripcion(EstadoInscripcionRequest request) {
        return crearEstadoInscripcionUseCase.execute(request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoInscripcionResponse> listarEstadosInscripcion() {
        return estadoInscripcionRepository.findAll().stream()
                .map(EstadoInscripcionResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoInscripcionResponse obtenerEstadoInscripcionPorId(String id) {
        return estadoInscripcionRepository.findById(id)
                .map(EstadoInscripcionResponse::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException(
                    "No se encontró un estado de inscripción con ID: " + id
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoInscripcionResponse obtenerEstadoInscripcionPorCodigo(String codigo) {
        return estadoInscripcionRepository.findByCodigo(codigo)
                .map(EstadoInscripcionResponse::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException(
                    "No se encontró un estado de inscripción con código: " + codigo
                ));
    }

    @Override
    public void eliminarEstadoInscripcion(String id) {
        eliminarEstadoInscripcionUseCase.execute(id);
    }
}

