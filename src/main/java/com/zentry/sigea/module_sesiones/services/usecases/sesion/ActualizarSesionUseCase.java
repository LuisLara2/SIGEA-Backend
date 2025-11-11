package com.zentry.sigea.module_sesiones.services.usecases.sesion;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Caso de uso para actualizar una sesión existente
 */
@Component
public class ActualizarSesionUseCase {
    
    private final ISesionRepository sesionRepository;

    public ActualizarSesionUseCase(ISesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public Optional<SesionDomainEntity> execute(String id, SesionDomainEntity datosActualizados) {
        if (id == null) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }
        
        return sesionRepository.findById(id).map(sesionExistente -> {
            // Usar el método de dominio para actualizar
            sesionExistente.updateInfo(
                datosActualizados.getFechaSesion(),
                datosActualizados.getTitulo()
            );
            
            return sesionRepository.save(sesionExistente);
        });
    }
}