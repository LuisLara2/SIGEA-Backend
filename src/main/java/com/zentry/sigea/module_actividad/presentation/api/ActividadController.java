package com.zentry.sigea.module_actividad.presentation.api;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_actividad.presentation.models.requestDTO.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.CrearActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.responseDTO.ActividadResponse;
import com.zentry.sigea.module_actividad.services.ActividadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * Controlador REST para gestionar actividades
 * Implementa la capa de presentación siguiendo Clean Architecture
 */
@RestController
@RequestMapping("/api/v1/actividades")
@CrossOrigin(origins = "*")
public class ActividadController {
    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    /**
     * Crear una nueva actividad
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Crear actividad",
        security = @SecurityRequirement(
            name = "organizadorJWT"
            ),
        tags = {"Crear"}
    )
    public ResponseEntity<ActividadResponse> crearActividad(@RequestBody CrearActividadRequest request) {
        try {
            String actividadId = actividadService.crearActividad(request);
            ActividadResponse response = new ActividadResponse();
            response.setId(actividadId);
            response.setDescripcion("Actividad registrada con éxito");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            ActividadResponse errorResponse = new ActividadResponse();
            errorResponse.setDescripcion("Error de validación: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            ActividadResponse errorResponse = new ActividadResponse();
            errorResponse.setDescripcion("Error interno: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Obtener una actividad por ID
     */
    @GetMapping("/obtener/{id}")
    @Operation(
        summary = "Obtener una actividad por su ID",
        tags = {"Obtener"}
    )
    public ResponseEntity<ActividadResponse> obtenerActividad(@PathVariable String id) {

        // Lista todas las actividades y filtra por ID
        List<ActividadResponse> actividad = actividadService.listarActividades();

        try {
            ActividadResponse actividadEncontrada = actividad.stream()
            .filter(a -> a.getId().equals(id))
            .findFirst()
            .orElse(null);
            return ResponseEntity.ok(actividadEncontrada); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }   

    }

    /**
     * Listar actividades con filtros opcionales
     */
    @GetMapping("/listar")
    @Operation(
        summary = "Listar actividades",
        tags = {"Listar"}
    )
    public ResponseEntity<List<ActividadResponse>> listarActividades() {
        
        // Implementar lógica de filtros cuando sea necesario
        List<ActividadResponse> actividades = actividadService.listarActividades();

        return ResponseEntity.ok(actividades);
        
    }

    /**
     * Endpoint de salud para verificar que el controlador funciona
     */
    @GetMapping("/health")
    @Operation(
        summary = "Verificar funcionamiento del modulo actividad",
        tags = {"Health"}
    )
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Actividades API is running");
    }

    @DeleteMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Eliminar una actividad por su ID",
        security = @SecurityRequirement(
            name = "organizadorJWT"
            ),
        tags = {"Eliminar"}
    )
    public ResponseEntity<Void> eliminarActividad(@PathVariable String id) {
        try {
            actividadService.eliminarActividad(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }

    /**
     * Actualizar una actividad existente
     */
    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Actualizar una actividad por su ID",
        security = @SecurityRequirement(
            name = "organizadorJWT"
            ),
        tags = {"Actualizar"}
    )
    public ResponseEntity<String> actualizarActividad(@PathVariable String id, @RequestBody ActividadRequest request) {
        try {
            String actividadActualizada = actividadService.actualizarActividad(id, request);
            return ResponseEntity.ok(actividadActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }
}