package com.zentry.sigea.module_notificaciones.services.usecases.notificacion;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_notificaciones.core.entities.EstadoNotificacionDomainEntity;
import com.zentry.sigea.module_notificaciones.core.entities.NotificacionDomainEntity;
import com.zentry.sigea.module_notificaciones.core.entities.TipoNotificacionDomainEntity;
import com.zentry.sigea.module_notificaciones.core.repositories.IEstadoNotificacionRepository;
import com.zentry.sigea.module_notificaciones.core.repositories.INotificacionRepository;
import com.zentry.sigea.module_notificaciones.core.repositories.ITipoNotificacionRepository;
import com.zentry.sigea.module_notificaciones.presentation.models.requestDTO.CrearNotificacionRequest;

/**
 * Caso de uso: Crear una nueva notificación
 * Responsabilidad: validar datos y orquestar la creación de notificación
 */
@Component
public class CrearNotificacionUseCase {

    private final INotificacionRepository notificacionRepository;
    private final ITipoNotificacionRepository tipoNotificacionRepository;
    private final IEstadoNotificacionRepository estadoNotificacionRepository;

    public CrearNotificacionUseCase(
        INotificacionRepository notificacionRepository,
        ITipoNotificacionRepository tipoNotificacionRepository,
        IEstadoNotificacionRepository estadoNotificacionRepository
    ) {
        this.notificacionRepository = notificacionRepository;
        this.tipoNotificacionRepository = tipoNotificacionRepository;
        this.estadoNotificacionRepository = estadoNotificacionRepository;
    }

    public String execute(CrearNotificacionRequest request) {
        // Validaciones básicas
        validateBasicFields(request);
        
        // Obtener tipo de notificación
        TipoNotificacionDomainEntity tipoNotificacion = 
            tipoNotificacionRepository.findById(request.getTipoNotificacionId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "No se encontró el tipo de notificación con ID: " + request.getTipoNotificacionId()
                ));
        
        // Obtener estado de notificación
        EstadoNotificacionDomainEntity estadoNotificacion = 
            estadoNotificacionRepository.findById(request.getEstadoNotificacionId())
                .orElseThrow(() -> new IllegalArgumentException(
                    "No se encontró el estado de notificación con ID: " + request.getEstadoNotificacionId()
                ));
        
        // Crear la notificación usando el factory method del dominio
        NotificacionDomainEntity nuevaNotificacion = NotificacionDomainEntity.create(
            request.getUsuarioId(),
            request.getActividadId(),
            tipoNotificacion,
            request.getMensaje(),
            estadoNotificacion
        );
        
        // Guardar
        return notificacionRepository.save(nuevaNotificacion) ? 
            "Notificación creada con éxito" : 
            "Error al guardar la notificación";
    }

    private void validateBasicFields(CrearNotificacionRequest request) {
        if (request.getUsuarioId() == null || request.getUsuarioId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio");
        }
        
        if (request.getTipoNotificacionId() == null || request.getTipoNotificacionId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del tipo de notificación es obligatorio");
        }
        
        if (request.getMensaje() == null || request.getMensaje().trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje es obligatorio");
        }
        
        if (request.getEstadoNotificacionId() == null || request.getEstadoNotificacionId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del estado es obligatorio");
        }
    }
}
