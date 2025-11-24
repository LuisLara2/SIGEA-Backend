package com.zentry.sigea.module_notificaciones.presentation.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_notificaciones.core.entities.EstadoNotificacionDomainEntity;
import com.zentry.sigea.module_notificaciones.core.repositories.IEstadoNotificacionRepository;

/**
 * Controlador REST para gestionar estados de notificación
 * Permite CRUD de catálogo de estados
 */
@RestController
@RequestMapping("/api/v1/estados-notificacion")
@CrossOrigin(origins = "*")
public class EstadoNotificacionController {
    
    private final IEstadoNotificacionRepository estadoRepository;

    public EstadoNotificacionController(IEstadoNotificacionRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    /**
     * Crear un nuevo estado de notificación
     * POST /api/v1/estados-notificacion
     */
    @PostMapping
    public ResponseEntity<?> crearEstado(@RequestBody EstadoRequest request) {
        try {
            if (request.getCodigo() == null || request.getCodigo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El código es obligatorio");
            }
            if (request.getEtiqueta() == null || request.getEtiqueta().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("La etiqueta es obligatoria");
            }

            EstadoNotificacionDomainEntity estado = EstadoNotificacionDomainEntity.create(
                request.getCodigo().toUpperCase(),
                request.getEtiqueta()
            );
            
            EstadoNotificacionDomainEntity estadoGuardado = estadoRepository.save(estado);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(new EstadoResponse(estadoGuardado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear estado: " + e.getMessage());
        }
    }

    /**
     * Listar todos los estados de notificación
     * GET /api/v1/estados-notificacion
     */
    @GetMapping
    public ResponseEntity<List<EstadoResponse>> listarEstados() {
        try {
            List<EstadoNotificacionDomainEntity> estados = estadoRepository.findAll();
            List<EstadoResponse> response = estados.stream()
                .map(EstadoResponse::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener un estado por ID
     * GET /api/v1/estados-notificacion/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEstado(@PathVariable String id) {
        try {
            return estadoRepository.findById(id)
                .map(estado -> ResponseEntity.ok(new EstadoResponse(estado)))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener estado: " + e.getMessage());
        }
    }

    /**
     * Buscar estado por código
     * GET /api/v1/estados-notificacion/codigo/{codigo}
     */
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> obtenerEstadoPorCodigo(@PathVariable String codigo) {
        try {
            return estadoRepository.findByCodigo(codigo.toUpperCase())
                .map(estado -> ResponseEntity.ok(new EstadoResponse(estado)))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar estado: " + e.getMessage());
        }
    }

    /**
     * Eliminar un estado
     * DELETE /api/v1/estados-notificacion/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstado(@PathVariable String id) {
        try {
            if (!estadoRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            estadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar estado: " + e.getMessage());
        }
    }

    /**
     * Health check
     * GET /api/v1/estados-notificacion/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Estados Notificación API is running");
    }

    // ========== DTOs ==========
    
    static class EstadoRequest {
        private String codigo;
        private String etiqueta;

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getEtiqueta() {
            return etiqueta;
        }

        public void setEtiqueta(String etiqueta) {
            this.etiqueta = etiqueta;
        }
    }

    static class EstadoResponse {
        private String id;
        private String codigo;
        private String etiqueta;

        public EstadoResponse(EstadoNotificacionDomainEntity estado) {
            this.id = estado.getId();
            this.codigo = estado.getCodigo();
            this.etiqueta = estado.getEtiqueta();
        }

        public String getId() {
            return id;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getEtiqueta() {
            return etiqueta;
        }
    }
}
