package com.zentry.sigea.module_inscripciones.services.usecases.inscripcion;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_inscripciones.core.repositories.IInscripcionRepository;

/**
 * Caso de uso para eliminar una inscripción
 */
@Component
public class EliminarInscripcionUseCase {

    private final IInscripcionRepository inscripcionRepository;

    public EliminarInscripcionUseCase(IInscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    /**
     * Ejecuta la eliminación de inscripción
     */
    public void execute(String id) {
        // Validar que la inscripción exista
        if (!inscripcionRepository.existsById(id)) {
            throw new IllegalArgumentException(
                "No se encontró una inscripción con ID: " + id
            );
        }

        // Eliminar la inscripción
        inscripcionRepository.deleteById(id);
    }
}

