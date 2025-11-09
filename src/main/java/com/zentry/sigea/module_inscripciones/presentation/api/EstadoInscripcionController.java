package com.zentry.sigea.module_inscripciones.presentation.api;

import java.util.List;

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

import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.EstadoInscripcionRequest;
import com.zentry.sigea.module_inscripciones.presentation.models.responseDTO.EstadoInscripcionResponse;
import com.zentry.sigea.module_inscripciones.services.EstadoInscripcionService;

/**
 * Controlador REST para gestionar estados de inscripción
 */
@RestController
@RequestMapping("/api/v1/estados-inscripcion")
@CrossOrigin(origins = "*")
public class EstadoInscripcionController {
    private final EstadoInscripcionService estadoInscripcionService;

    public EstadoInscripcionController(EstadoInscripcionService estadoInscripcionService) {
        this.estadoInscripcionService = estadoInscripcionService;
    }

    /**
     * Crear un nuevo estado de inscripción
     */
    @PostMapping("/create")
    public ResponseEntity<String> crearEstadoInscripcion(
        @RequestBody EstadoInscripcionRequest request
    ) {
        try {
            String responseMessage = estadoInscripcionService.crearEstadoInscripcion(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor");
        }
    }

    /**
     * Listar todos los estados de inscripción
     */
    @GetMapping
    public ResponseEntity<List<EstadoInscripcionResponse>> listarEstadosInscripcion() {
        List<EstadoInscripcionResponse> estados = estadoInscripcionService.listarEstadosInscripcion();
        return ResponseEntity.ok(estados);
    }

    /**
     * Obtener un estado de inscripción por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<EstadoInscripcionResponse> obtenerEstadoInscripcionPorId(
        @PathVariable String id
    ) {
        try {
            EstadoInscripcionResponse estado = 
                estadoInscripcionService.obtenerEstadoInscripcionPorId(id);
            return ResponseEntity.ok(estado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener un estado de inscripción por código
     */
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<EstadoInscripcionResponse> obtenerEstadoInscripcionPorCodigo(
        @PathVariable String codigo
    ) {
        try {
            EstadoInscripcionResponse estado = 
                estadoInscripcionService.obtenerEstadoInscripcionPorCodigo(codigo);
            return ResponseEntity.ok(estado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminar un estado de inscripción
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstadoInscripcion(@PathVariable String id) {
        try {
            estadoInscripcionService.eliminarEstadoInscripcion(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint de salud
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Estados de Inscripción API is running");
    }
}

