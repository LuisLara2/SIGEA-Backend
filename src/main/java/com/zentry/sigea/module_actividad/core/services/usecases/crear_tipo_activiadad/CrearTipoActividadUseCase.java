package com.zentry.sigea.module_actividad.core.services.usecases.crear_tipo_activiadad;

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
        // validateBusinessRules(request);
        
        // Crear la entidad usando el factory method del dominio
        TipoActividad nuevoTipoActividad = TipoActividad.create(
            request.getNombreActividad(),
            request.getDescripcion(),
            request.getCreatedAt(),
            request.getUpdatedAt()
        );
        
        // Guardar usando el repositorio directamente
        return tipoActividadRepository.save(nuevoTipoActividad);
    }

    // private void validateBusinessRules(TipoActividadRequest request) {
    //     // Validar que no exista otro tipo de actividad con el mismo nombre
    //     var tiposExistentes = tipoActividadRepository.findByNombre(request.getNombreActividad());
    //     if (!tiposExistentes.isEmpty()) {
    //         throw new IllegalArgumentException(
    //             "Ya existe un tipo de actividad con el nombre: " + request.getNombreActividad()
    //         );
    //     }
    // }
    
}
