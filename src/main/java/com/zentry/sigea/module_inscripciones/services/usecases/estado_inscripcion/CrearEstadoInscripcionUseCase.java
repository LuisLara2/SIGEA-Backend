package com.zentry.sigea.module_inscripciones.services.usecases.estado_inscripcion;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_inscripciones.core.entities.EstadoInscripcionDomainEntity;
import com.zentry.sigea.module_inscripciones.core.repositories.IEstadoInscripcionRepository;
import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.EstadoInscripcionRequest;

/**
 * Caso de uso para crear un nuevo estado de inscripción
 */
@Component
public class CrearEstadoInscripcionUseCase {

    private final IEstadoInscripcionRepository estadoInscripcionRepository;

    public CrearEstadoInscripcionUseCase(IEstadoInscripcionRepository estadoInscripcionRepository) {
        this.estadoInscripcionRepository = estadoInscripcionRepository;
    }

    public String execute(EstadoInscripcionRequest request) {
        // Validar que el código no exista ya
        if (estadoInscripcionRepository.findByCodigo(request.getCodigo()).isPresent()) {
            throw new IllegalArgumentException(
                "Ya existe un estado de inscripción con el código: " + request.getCodigo()
            );
        }

        // Crear la entidad usando el factory method del dominio
        EstadoInscripcionDomainEntity nuevoEstado = EstadoInscripcionDomainEntity.create(
            request.getCodigo(),
            request.getEtiqueta()
        );

        // Guardar usando el repositorio
        return estadoInscripcionRepository.save(nuevoEstado) ? 
            "Estado de inscripción registrado con éxito" : 
            "Algo salió mal al guardar el estado de inscripción";
    }
}

