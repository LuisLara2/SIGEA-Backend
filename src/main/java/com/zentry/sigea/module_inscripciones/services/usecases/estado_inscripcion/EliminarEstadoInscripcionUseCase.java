package com.zentry.sigea.module_inscripciones.services.usecases.estado_inscripcion;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_inscripciones.core.repositories.IEstadoInscripcionRepository;

/**
 * Caso de uso para eliminar un estado de inscripción
 */
@Component
public class EliminarEstadoInscripcionUseCase {

    private final IEstadoInscripcionRepository estadoInscripcionRepository;

    public EliminarEstadoInscripcionUseCase(IEstadoInscripcionRepository estadoInscripcionRepository) {
        this.estadoInscripcionRepository = estadoInscripcionRepository;
    }

    public void execute(String id) {
        // Validar que el estado exista
        if (estadoInscripcionRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException(
                "No se encontró un estado de inscripción con ID: " + id
            );
        }

        // Eliminar el estado
        estadoInscripcionRepository.deleteById(id);
    }
}

