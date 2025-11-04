package com.zentry.sigea.module_actividad.services.usecases.tipo_actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.TipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;

/**
 * Caso de uso para crear un nuevo tipo de actividad
 */
@Component
public class CrearTipoActividadUseCase {

    private final TipoActividadRepository tipoActividadRepository;

    public CrearTipoActividadUseCase(TipoActividadRepository tipoActividadRepository) {
        this.tipoActividadRepository = tipoActividadRepository;
    }

    public TipoActividadDomainEntity execute(TipoActividadRequest request) {
        // Validaciones de negocio específicas del caso de uso
        validateBusinessRules(request);
        
        // Crear la entidad usando el factory method del dominio
        TipoActividadDomainEntity nuevoTipoActividad = TipoActividadDomainEntity.create(
            request.getNombreActividad(),
            request.getDescripcion()
        );
        
        // Guardar usando el repositorio directamente
        return tipoActividadRepository.save(nuevoTipoActividad);
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
