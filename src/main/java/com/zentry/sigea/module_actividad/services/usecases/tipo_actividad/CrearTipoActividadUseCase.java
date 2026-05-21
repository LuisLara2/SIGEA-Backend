package com.zentry.sigea.module_actividad.services.usecases.tipo_actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.TipoActividadRequest;

/**
 * Caso de uso para crear un nuevo tipo de actividad
 */
@Component
public class CrearTipoActividadUseCase {

    private final ITipoActividadRepository tipoActividadRepository;

    public CrearTipoActividadUseCase(ITipoActividadRepository tipoActividadRepository) {
        this.tipoActividadRepository = tipoActividadRepository;
    }

    public String execute(TipoActividadRequest request) {
        // Validaciones de negocio específicas del caso de uso
        validateBusinessRules(request);
        
        // Crear la entidad usando el factory method del dominio
        TipoActividadDomainEntity nuevoTipoActividad = TipoActividadDomainEntity.create(
            request.getNombreActividad(),
            request.getDescripcion()
        );
        
        // Guardar usando el repositorio directamente
        
        return tipoActividadRepository.save(nuevoTipoActividad) ? String.format("El tipo de actividad %s se registro correctamente.", request.getNombreActividad()) : "Ocurrio un error al registrar el tipo de actividad.";
    }

    private void validateBusinessRules(TipoActividadRequest request) {
        if (request.getNombreActividad() == null || request.getNombreActividad().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la actividad es obligatorio");
        }
        
        // var tiposExistentes = tipoActividadRepository.findByNombre(request.getNombreActividad());
        // if (!tiposExistentes.isEmpty()) {
        //     throw new IllegalArgumentException(
        //         "Ya existe un tipo de actividad con el nombre: " + request.getNombreActividad()
        //     );
        // }
    }
    
}
