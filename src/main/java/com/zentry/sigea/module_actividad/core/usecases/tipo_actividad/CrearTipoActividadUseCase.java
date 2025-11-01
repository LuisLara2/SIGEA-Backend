package com.zentry.sigea.module_actividad.core.usecases.tipo_actividad;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.core.repositories.TipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;

@Component
public class CrearTipoActividadUseCase {

    private final TipoActividadRepository tipoActividadRepository;

    public CrearTipoActividadUseCase(TipoActividadRepository tipoActividadRepository) {
        this.tipoActividadRepository = tipoActividadRepository;
    }

    public TipoActividad execute(TipoActividadRequest request) {
        // Validaciones de negocio específicas del caso de uso
        validateBusinessRules(request);
        
        // Crear la entidad usando el factory method del dominio
        TipoActividad nuevoTipoActividad = TipoActividad.create(
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
        
        // TODO: Implementar findByNombre en el repositorio
        // var tiposExistentes = tipoActividadRepository.findByNombre(request.getNombreActividad());
        // if (!tiposExistentes.isEmpty()) {
        //     throw new IllegalArgumentException(
        //         "Ya existe un tipo de actividad con el nombre: " + request.getNombreActividad()
        //     );
        // }
    }
    
}
