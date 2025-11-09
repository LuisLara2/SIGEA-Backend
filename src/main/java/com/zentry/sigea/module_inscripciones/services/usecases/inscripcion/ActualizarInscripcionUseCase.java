package com.zentry.sigea.module_inscripciones.services.usecases.inscripcion;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_inscripciones.core.entities.EstadoInscripcionDomainEntity;
import com.zentry.sigea.module_inscripciones.core.entities.InscripcionDomainEntity;
import com.zentry.sigea.module_inscripciones.core.repositories.IInscripcionRepository;
import com.zentry.sigea.module_inscripciones.core.repositories.IEstadoInscripcionRepository;
import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.InscripcionRequest;
import com.zentry.sigea.module_inscripciones.presentation.models.responseDTO.InscripcionResponse;

/**
 * Caso de uso para actualizar una inscripción existente
 */
@Component
public class ActualizarInscripcionUseCase {

    private final IInscripcionRepository inscripcionRepository;
    private final IEstadoInscripcionRepository estadoInscripcionRepository;

    public ActualizarInscripcionUseCase(
        IInscripcionRepository inscripcionRepository,
        IEstadoInscripcionRepository estadoInscripcionRepository
    ) {
        this.inscripcionRepository = inscripcionRepository;
        this.estadoInscripcionRepository = estadoInscripcionRepository;
    }

    /**
     * Ejecuta la actualización de inscripción
     */
    public InscripcionResponse execute(InscripcionRequest request, String id) {
        // Validar que la inscripción exista
        InscripcionDomainEntity inscripcion = inscripcionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "No se encontró una inscripción con ID: " + id
            ));

        // Actualizar fecha de inscripción si se proporciona
        if (request.getFechaInscripcion() != null) {
            inscripcion.setFechaInscripcion(request.getFechaInscripcion());
        }

        // Actualizar estado si se proporciona
        if (request.getEstadoId() != null && !request.getEstadoId().trim().isEmpty()) {
            EstadoInscripcionDomainEntity nuevoEstado = estadoInscripcionRepository
                .findById(request.getEstadoId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "No se encontró un estado de inscripción con ID: " + request.getEstadoId()
                ));
            inscripcion.cambiarEstado(nuevoEstado);
        }

        // Guardar cambios
        inscripcionRepository.save(inscripcion);

        return InscripcionResponse.fromEntity(inscripcion);
    }
}

