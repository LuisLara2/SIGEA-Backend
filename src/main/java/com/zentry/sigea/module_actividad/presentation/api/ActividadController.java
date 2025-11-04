package com.zentry.sigea.module_actividad.presentation.api;
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

import com.zentry.sigea.module_actividad.core.entities.actividad.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.presentation.models.ActividadResponse;
import com.zentry.sigea.module_actividad.presentation.models.CrearActividadRequest;
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
    @PostMapping("/create")
    public ResponseEntity<ActividadResponse> crearActividad(@RequestBody CrearActividadRequest request) {
        try {
            // Ejecutar el caso de uso a través del servicio pasando el request con IDs
            ActividadDomainEntity actividadCreada = actividadService.crearActividad(request);

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
        return actividadService.obtenerActividadPorId(id)
                .getId() != null ?
                ResponseEntity.ok(actividadService.obtenerActividadPorId(id)) :
                ResponseEntity.notFound().build();
    }

    /**
     * Listar actividades con filtros opcionales
     */
    @GetMapping
    public ResponseEntity<List<ActividadResponse>> listarActividades() {
        
        // Implementar lógica de filtros cuando sea necesario
        List<ActividadResponse> actividades = actividadService.listarActividades();

        return ResponseEntity.ok(actividades);
        
    }

    /**
     * Endpoint de salud para verificar que el controlador funciona
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Actividades API is running");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarActividad(@PathVariable Long id) {
        try {
            actividadService.eliminarActividad(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}