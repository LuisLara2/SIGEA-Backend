package com.zentry.sigea.module_inscripciones.services.usecases.inscripcion;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_inscripciones.core.entities.InscripcionDomainEntity;
import com.zentry.sigea.module_inscripciones.core.repositories.IInscripcionRepository;

/**
 * Caso de uso para obtener una inscripción por su ID
 */
@Component
public class ObtenerInscripcionPorIdUseCase {

    private final IInscripcionRepository inscripcionRepository;

    public ObtenerInscripcionPorIdUseCase(IInscripcionRepository inscripcionRepository) {
        this.inscripcionRepository = inscripcionRepository;
    }

    /**
     * Ejecuta la obtención de inscripción por ID
     */
    public Optional<InscripcionDomainEntity> execute(String id) {
        return inscripcionRepository.findById(id);
    }
}

