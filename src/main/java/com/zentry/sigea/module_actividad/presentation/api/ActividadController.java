package com.zentry.sigea.module_actividad.presentation.api;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.presentation.models.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.ActividadResponse;
import com.zentry.sigea.module_actividad.services.ActividadService;

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
    @PostMapping
    public ResponseEntity<ActividadResponse> crearActividad(@RequestBody ActividadRequest request) {
        try {
            // Ejecutar el caso de uso a través del servicio pasando directamente el request
            Actividad actividadCreada = actividadService.crearActividad(request);

            // Convertir entidad a response
            ActividadResponse response = ActividadResponse.fromEntity(actividadCreada);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtener una actividad por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponse> obtenerActividad(@PathVariable Long id) {
        return actividadService.obtenerPorId(id)
                .map(actividad -> ResponseEntity.ok(ActividadResponse.fromEntity(actividad)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Listar actividades con filtros opcionales
     */
    @GetMapping
    public ResponseEntity<List<ActividadResponse>> listarActividades(
            @RequestParam(required = false) Long estadoId,
            @RequestParam(required = false) Long tipoId) {
        
        // Implementar lógica de filtros cuando sea necesario
        var actividades = actividadService.listarActividades(Optional.empty(), Optional.empty());
        var response = actividades.stream()
                .map(ActividadResponse::fromEntity)
                .toList();
        
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint de salud para verificar que el controlador funciona
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Actividades API is running");
    }
}