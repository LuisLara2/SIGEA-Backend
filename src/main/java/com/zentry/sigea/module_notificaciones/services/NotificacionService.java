package com.zentry.sigea.module_notificaciones.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_notificaciones.presentation.models.requestDTO.ActualizarNotificacionRequest;
import com.zentry.sigea.module_notificaciones.presentation.models.requestDTO.CrearNotificacionRequest;
import com.zentry.sigea.module_notificaciones.presentation.models.responseDTO.NotificacionResponse;
import com.zentry.sigea.module_notificaciones.services.usecases.notificacion.ActualizarNotificacionUseCase;
import com.zentry.sigea.module_notificaciones.services.usecases.notificacion.CrearNotificacionUseCase;
import com.zentry.sigea.module_notificaciones.services.usecases.notificacion.EliminarNotificacionUseCase;
import com.zentry.sigea.module_notificaciones.services.usecases.notificacion.ListarNotificacionesUseCase;
import com.zentry.sigea.module_notificaciones.services.usecases.notificacion.ObtenerNotificacionPorIdUseCase;

/**
 * Servicio de aplicación para notificaciones
 * Orquesta los casos de uso y convierte entre dominio y DTOs
 */
@Service
public class NotificacionService {
    
    private final CrearNotificacionUseCase crearNotificacionUseCase;
    private final ObtenerNotificacionPorIdUseCase obtenerNotificacionPorIdUseCase;
    private final ListarNotificacionesUseCase listarNotificacionesUseCase;
    private final ActualizarNotificacionUseCase actualizarNotificacionUseCase;
    private final EliminarNotificacionUseCase eliminarNotificacionUseCase;

    public NotificacionService(
        CrearNotificacionUseCase crearNotificacionUseCase,
        ObtenerNotificacionPorIdUseCase obtenerNotificacionPorIdUseCase,
        ListarNotificacionesUseCase listarNotificacionesUseCase,
        ActualizarNotificacionUseCase actualizarNotificacionUseCase,
        EliminarNotificacionUseCase eliminarNotificacionUseCase
    ) {
        this.crearNotificacionUseCase = crearNotificacionUseCase;
        this.obtenerNotificacionPorIdUseCase = obtenerNotificacionPorIdUseCase;
        this.listarNotificacionesUseCase = listarNotificacionesUseCase;
        this.actualizarNotificacionUseCase = actualizarNotificacionUseCase;
        this.eliminarNotificacionUseCase = eliminarNotificacionUseCase;
    }

    public String crearNotificacion(CrearNotificacionRequest request) {
        return crearNotificacionUseCase.execute(request);
    }

    public NotificacionResponse obtenerNotificacionPorId(String id) {
        return NotificacionResponse.fromEntity(
            obtenerNotificacionPorIdUseCase.execute(id)
        );
    }

    public List<NotificacionResponse> listarNotificaciones() {
        return listarNotificacionesUseCase.execute()
            .stream()
            .map(NotificacionResponse::fromEntity)
            .collect(Collectors.toList());
    }

    public List<NotificacionResponse> obtenerNotificacionesPorUsuario(String usuarioId) {
        return listarNotificacionesUseCase.executeByUsuarioId(usuarioId)
            .stream()
            .map(NotificacionResponse::fromEntity)
            .collect(Collectors.toList());
    }

    public List<NotificacionResponse> obtenerNotificacionesPorActividad(String actividadId) {
        return listarNotificacionesUseCase.executeByActividadId(actividadId)
            .stream()
            .map(NotificacionResponse::fromEntity)
            .collect(Collectors.toList());
    }

    public NotificacionResponse actualizarNotificacion(String id, ActualizarNotificacionRequest request) {
        return NotificacionResponse.fromEntity(
            actualizarNotificacionUseCase.execute(id, request)
        );
    }

    public void eliminarNotificacion(String id) {
        eliminarNotificacionUseCase.execute(id);
    }
}
