package com.zentry.sigea.module_sesiones.presentacion.api;

import com.zentry.sigea.module_sesiones.presentacion.models.CrearSesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionResponse;
import com.zentry.sigea.module_sesiones.services.SesionService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para gestionar sesiones
 */
@RestController
@RequestMapping("/api/v1/sesiones")
@CrossOrigin(origins = "*")
public class SesionController {
    
    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }


    @PostMapping
    public ResponseEntity<SesionResponse> crearSesion(@Valid @RequestBody CrearSesionRequest request) {
        try {
            SesionResponse sesionCreada = sesionService.crearSesion(request);
            return ResponseEntity
                .created(URI.create("/api/v1/sesiones/" + sesionCreada.getId()))
                .body(sesionCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SesionResponse>> listarSesiones(
        @RequestParam(required = false) String actividadId
    ) {
        try {
            List<SesionResponse> sesiones;
            
            if (actividadId != null) {
                sesiones = sesionService.listarSesionesPorActividad(actividadId);
            } else {
                sesiones = sesionService.listarSesiones();
            }
            
            return ResponseEntity.ok(sesiones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
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


    @PutMapping("/{id}")
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


    @DeleteMapping("/{id}")
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