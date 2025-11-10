package com.zentry.sigea.module_sesiones.services.usecases.sesion;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import com.zentry.sigea.module_sesiones.presentacion.models.CrearSesionRequest;

import java.time.LocalDateTime;


@Component
public class CrearSesionUseCase {

    private final ISesionRepository sesionRepository;

    public CrearSesionUseCase(ISesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    /**
     * Ejecuta la creación de una sesión
     */
    public SesionDomainEntity execute(CrearSesionRequest request) {

        validateRequest(request);
        validateBusinessRules(request);
        
        // Crear la entidad de dominio usando el factory method
        SesionDomainEntity nuevaSesion = SesionDomainEntity.create(
            request.getActividadId(),
            request.getFechaSesion(),
            request.getTitulo()
        );
        return sesionRepository.save(nuevaSesion);
    }

    private void validateRequest(CrearSesionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("El request no puede ser nulo");
        }
        if (request.getActividadId() == null) {
            throw new IllegalArgumentException("El ID de actividad debe ser un número positivo");
        }
        if (request.getFechaSesion() == null) {
            throw new IllegalArgumentException("La fecha de sesión es obligatoria");
        }
        if (request.getTitulo() == null || request.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El título es obligatorio");
        }
    }

    private void validateBusinessRules(CrearSesionRequest request) {
        if (request.getFechaSesion().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se pueden crear sesiones en fechas pasadas");
        }
        
        LocalDateTime unAnioDelante = LocalDateTime.now().plusYears(1);
        if (request.getFechaSesion().isAfter(unAnioDelante)) {
            throw new IllegalArgumentException(
                "No se pueden crear sesiones con más de 1 año de anticipación"
            );
        }
        
    }
}
