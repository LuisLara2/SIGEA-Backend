package com.zentry.sigea.module_notificaciones.presentation.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_notificaciones.presentation.models.requestDTO.ActualizarNotificacionRequest;
import com.zentry.sigea.module_notificaciones.presentation.models.requestDTO.CrearNotificacionRequest;
import com.zentry.sigea.module_notificaciones.presentation.models.responseDTO.NotificacionResponse;
import com.zentry.sigea.module_notificaciones.services.NotificacionService;

/**
 * Controlador REST para gestionar notificaciones
 * Expone endpoints CRUD siguiendo principios REST
 */
@RestController
@RequestMapping("/api/v1/notificaciones")
@CrossOrigin(origins = "*")
public class NotificacionController {
    
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    /**
     * Crear una nueva notificación
     */
    @PostMapping("/create")
    public ResponseEntity<String> crearNotificacion(@RequestBody CrearNotificacionRequest request) {
        try {
            String responseMessage = notificacionService.crearNotificacion(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + e.getMessage());
        }
    }

    /**
     * Obtener una notificación por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponse> obtenerNotificacion(@PathVariable String id) {
        try {
            NotificacionResponse notificacion = notificacionService.obtenerNotificacionPorId(id);
            return ResponseEntity.ok(notificacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Listar todas las notificaciones
     */
    @GetMapping
    public ResponseEntity<List<NotificacionResponse>> listarNotificaciones() {
        try {
            List<NotificacionResponse> notificaciones = notificacionService.listarNotificaciones();
            return ResponseEntity.ok(notificaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener notificaciones por usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificacionResponse>> obtenerNotificacionesPorUsuario(
        @PathVariable String usuarioId
    ) {
        try {
            List<NotificacionResponse> notificaciones = 
                notificacionService.obtenerNotificacionesPorUsuario(usuarioId);
            return ResponseEntity.ok(notificaciones);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener notificaciones por actividad
     */
    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<NotificacionResponse>> obtenerNotificacionesPorActividad(
        @PathVariable String actividadId
    ) {
        try {
            List<NotificacionResponse> notificaciones = 
                notificacionService.obtenerNotificacionesPorActividad(actividadId);
            return ResponseEntity.ok(notificaciones);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener notificaciones por tipo de evento
     * @param tipoEvento Ejemplos: CERTIFICADO_GENERADO, SESION_CREADA, INSCRIPCION_APROBADA, etc.
     */
    @GetMapping("/tipo/{tipoEvento}")
    public ResponseEntity<List<NotificacionResponse>> obtenerNotificacionesPorTipo(
        @PathVariable String tipoEvento
    ) {
        try {
            List<NotificacionResponse> notificaciones = 
                notificacionService.obtenerNotificacionesPorTipo(tipoEvento);
            return ResponseEntity.ok(notificaciones);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener notificaciones de un usuario filtradas por tipo de evento
     * @param usuarioId ID del usuario
     * @param tipoEvento Tipo de evento a filtrar
     */
    @GetMapping("/usuario/{usuarioId}/tipo/{tipoEvento}")
    public ResponseEntity<List<NotificacionResponse>> obtenerNotificacionesPorUsuarioYTipo(
        @PathVariable String usuarioId,
        @PathVariable String tipoEvento
    ) {
        try {
            List<NotificacionResponse> notificaciones = 
                notificacionService.obtenerNotificacionesPorUsuarioYTipo(usuarioId, tipoEvento);
            return ResponseEntity.ok(notificaciones);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Marcar una notificación como leída
     */
    @PutMapping("/{id}/marcar-leida")
    public ResponseEntity<NotificacionResponse> marcarComoLeida(@PathVariable String id) {
        try {
            NotificacionResponse notificacion = notificacionService.marcarComoLeida(id);
            return ResponseEntity.ok(notificacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Marcar todas las notificaciones de un usuario como leídas
     */
    @PutMapping("/usuario/{usuarioId}/marcar-todas-leidas")
    public ResponseEntity<String> marcarTodasComoLeidas(@PathVariable String usuarioId) {
        try {
            notificacionService.marcarTodasComoLeidas(usuarioId);
            return ResponseEntity.ok("Todas las notificaciones marcadas como leídas");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminar todas las notificaciones de un usuario
     */
    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> eliminarNotificacionesUsuario(@PathVariable String usuarioId) {
        try {
            notificacionService.eliminarNotificacionesPorUsuario(usuarioId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Actualizar una notificación existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponse> actualizarNotificacion(
        @PathVariable String id,
        @RequestBody ActualizarNotificacionRequest request
    ) {
        try {
            NotificacionResponse notificacion = 
                notificacionService.actualizarNotificacion(id, request);
            return ResponseEntity.ok(notificacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminar una notificación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable String id) {
        try {
            notificacionService.eliminarNotificacion(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint de salud del servicio
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Notificaciones API is running");
    }
}
