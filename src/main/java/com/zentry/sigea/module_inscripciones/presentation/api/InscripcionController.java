package com.zentry.sigea.module_inscripciones.presentation.api;

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

import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.CrearInscripcionRequest;
import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.InscripcionRequest;
import com.zentry.sigea.module_inscripciones.presentation.models.responseDTO.InscripcionResponse;
import com.zentry.sigea.module_inscripciones.services.InscripcionService;

/**
 * Controlador REST para gestionar inscripciones
 * Implementa la capa de presentación siguiendo Clean Architecture
 */
@RestController
@RequestMapping("/api/v1/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {
    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    /**
     * Crear una nueva inscripción
     */
    @PostMapping("/create")
    public ResponseEntity<String> crearInscripcion(@RequestBody CrearInscripcionRequest request) {
        try {
            String responseMessage = inscripcionService.crearInscripcion(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor");
        }
    }

    /**
     * Obtener una inscripción por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionResponse> obtenerInscripcion(@PathVariable String id) {
        try {
            InscripcionResponse inscripcion = inscripcionService.obtenerInscripcionPorId(id);
            return ResponseEntity.ok(inscripcion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Listar todas las inscripciones
     */
    @GetMapping
    public ResponseEntity<List<InscripcionResponse>> listarInscripciones() {
        List<InscripcionResponse> inscripciones = inscripcionService.listarInscripciones();
        return ResponseEntity.ok(inscripciones);
    }

    /**
     * Obtener inscripciones por usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<InscripcionResponse>> obtenerInscripcionesPorUsuario(
        @PathVariable String usuarioId
    ) {
        try {
            List<InscripcionResponse> inscripciones = 
                inscripcionService.obtenerInscripcionesPorUsuario(usuarioId);
            return ResponseEntity.ok(inscripciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener inscripciones por actividad
     */
    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<InscripcionResponse>> obtenerInscripcionesPorActividad(
        @PathVariable String actividadId
    ) {
        try {
            List<InscripcionResponse> inscripciones = 
                inscripcionService.obtenerInscripcionesPorActividad(actividadId);
            return ResponseEntity.ok(inscripciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Actualizar una inscripción
     */
    @PutMapping("/{id}")
    public ResponseEntity<InscripcionResponse> actualizarInscripcion(
        @PathVariable String id,
        @RequestBody InscripcionRequest request
    ) {
        try {
            InscripcionResponse inscripcion = inscripcionService.actualizarInscripcion(id, request);
            return ResponseEntity.ok(inscripcion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminar una inscripción
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable String id) {
        try {
            inscripcionService.eliminarInscripcion(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint de salud para verificar que el controlador funciona
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Inscripciones API is running");
    }
}

