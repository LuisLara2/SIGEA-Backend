package com.zentry.sigea.module_sesiones.services.usecases.sesion;

import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para eliminar una sesión
 */
@Component
public class EliminarSesionUseCase {
    
    private final ISesionRepository sesionRepository;

    public EliminarSesionUseCase(ISesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public void execute(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        
        if (!sesionRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró sesión con ID: " + id);
        }
        
        sesionRepository.deleteById(id);
    }
}