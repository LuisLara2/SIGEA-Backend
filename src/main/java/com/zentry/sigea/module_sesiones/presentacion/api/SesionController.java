package com.zentry.sigea.module_sesiones.presentacion.api;

import com.zentry.sigea.module_sesiones.presentacion.models.CrearSesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionResponse;
import com.zentry.sigea.module_sesiones.services.SesionService;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST para gestionar sesiones
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/sesiones")
@CrossOrigin(origins = "*")
public class SesionController {
    
    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Crear sesion",
        security = @SecurityRequirement(
            name = "organizadorJWT"
        ),
        tags = {"Crear"}
    )
    public ResponseEntity<SesionResponse> crearSesion(@Valid @RequestBody CrearSesionRequest request) {
        try {
            log.debug("Iniciando creación de sesión con request: {}", request);
            SesionResponse sesionCreada = sesionService.crearSesion(request);
            log.debug("Sesión creada exitosamente: {}", sesionCreada);
            return ResponseEntity
                .created(URI.create("/api/v1/sesiones/" + sesionCreada.getId()))
                .body(sesionCreada);
        } catch (IllegalArgumentException e) {
            log.error("Error en la solicitud: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error interno al crear la sesión", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @GetMapping("/listar")
    @Operation(
        summary = "Listar sesiones",
        description = "Lista todas las sesiones. Permite filtrar por actividad, modalidad, y otros parámetros.",
        tags = {"Listar"}
    )
    public ResponseEntity<List<SesionResponse>> listarSesiones(
        @RequestParam(required = false) String actividadId,
        @RequestParam(required = false) Modalidad modalidad
    ) {
        try {
            List<SesionResponse> sesiones = sesionService.listarSesiones(actividadId, modalidad);
            return ResponseEntity.ok(sesiones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/obtener/{id}")
    @Operation(
        summary = "Obtener una sesion por su ID",
        tags = {"Obtener"}
    )
    public ResponseEntity<SesionResponse> obtenerSesion(@PathVariable String id) {
        try {
            SesionResponse sesion = sesionService.obtenerSesionPorId(id);
            return ResponseEntity.ok(sesion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Actualizar sesion por su ID",
        security = @SecurityRequirement(
            name = "organizadorJWT"
        ),
        tags = {"Actualizar"}
    )
    public ResponseEntity<SesionResponse> actualizarSesion(
        @PathVariable String id,
        @Valid @RequestBody SesionRequest request
    ) {
        try {
            SesionResponse sesionActualizada = sesionService.actualizarSesion(id, request);
            return ResponseEntity.ok(sesionActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Eliminar una sesion por su ID",
        security = @SecurityRequirement(
            name = "organizadorJWT"
        ),
        tags = {"Eliminar"}
    )
    public ResponseEntity<Void> eliminarSesion(@PathVariable String id) {
        try {
            sesionService.eliminarSesion(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}