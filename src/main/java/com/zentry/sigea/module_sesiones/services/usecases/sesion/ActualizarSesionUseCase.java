package com.zentry.sigea.module_sesiones.services.usecases.sesion;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Caso de uso para actualizar una sesión existente.
 */
@Component
public class ActualizarSesionUseCase {
    
    private final ISesionRepository sesionRepository;

    public ActualizarSesionUseCase(ISesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public Optional<SesionDomainEntity> execute(String id, SesionDomainEntity datosActualizados) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID de la sesión no puede ser nulo o vacío.");
        }

        if (datosActualizados == null) {
            throw new IllegalArgumentException("Los datos actualizados no pueden ser nulos.");
        }

        // Validación adicional de campos importantes
        if (datosActualizados.getTitulo() == null || datosActualizados.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }

        return sesionRepository.findById(id).map(sesionExistente -> {
            sesionExistente.updateInfo(
                datosActualizados.getTitulo(),
                datosActualizados.getDescripcion(),
                datosActualizados.getFechaSesion(),
                datosActualizados.getHoraInicio(),
                datosActualizados.getHoraFin(),
                datosActualizados.getPonente(),
                datosActualizados.getModalidad(),
                datosActualizados.getLugarSesion(),
                datosActualizados.getLinkVirtual(),
                datosActualizados.getOrden()
            );
            
            return sesionRepository.save(sesionExistente);
        });
    }
}