package com.zentry.sigea.modules.sesiones.presentation.api;

import com.zentry.sigea.modules.sesiones.core.entities.Sesion;
import com.zentry.sigea.modules.sesiones.services.usecases.CrearSesionUseCase;
import com.zentry.sigea.modules.sesiones.services.usecases.ListarSesionesUseCase;
import com.zentry.sigea.modules.sesiones.services.usecases.ActualizarSesionUseCase;
import com.zentry.sigea.modules.sesiones.services.usecases.EliminarSesionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
@CrossOrigin(origins = "*")
public class SesionController {
    private final CrearSesionUseCase crearUseCase;
    private final ListarSesionesUseCase listarUseCase;
    private final ActualizarSesionUseCase actualizarUseCase;
    private final EliminarSesionUseCase eliminarUseCase;

    public SesionController(CrearSesionUseCase crearUseCase, ListarSesionesUseCase listarUseCase,
                            ActualizarSesionUseCase actualizarUseCase, EliminarSesionUseCase eliminarUseCase) {
        this.crearUseCase = crearUseCase;
        this.listarUseCase = listarUseCase;
        this.actualizarUseCase = actualizarUseCase;
        this.eliminarUseCase = eliminarUseCase;
    }

    // Adaptador REST para el recurso Sesion: expone endpoints CRUD y mapea DTOs <-> dominio.

    @PostMapping
    public ResponseEntity<SesionDTO> crear(@RequestBody SesionRequest request) {
            // Crea una nueva sesión y devuelve 201 con Location.
        Sesion domain = Sesion.create(request.getActividadId(), request.getFechaSesion(), request.getTitulo());
        var saved = crearUseCase.execute(domain);
        var dto = new SesionDTO(saved.getId(), saved.getActividadId(), saved.getFechaSesion(), saved.getTitulo());
        return ResponseEntity.created(URI.create("/api/sesiones/" + dto.getId())).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<SesionDTO>> listar() {
    // Lista todas las sesiones.
        var list = listarUseCase.execute();
        List<SesionDTO> dtos = list.stream().map(s -> new SesionDTO(s.getId(), s.getActividadId(), s.getFechaSesion(), s.getTitulo())).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionDTO> obtenerPorId(@PathVariable Long id) {
    // Obtiene una sesión por id; 200 si existe, 404 si no.
        var sesionOpt = listarUseCase.obtenerPorId(id);
        return sesionOpt
                .map(s -> ResponseEntity.ok(new SesionDTO(
                        s.getId(),
                        s.getActividadId(),
                        s.getFechaSesion(),
                        s.getTitulo()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionDTO> actualizar(@PathVariable Long id, @RequestBody SesionRequest request) {
    // Actualiza una sesión existente; devuelve 200 con la sesión actualizada o 404.
        Sesion updated = Sesion.create(request.getActividadId(), request.getFechaSesion(), request.getTitulo());
        var result = actualizarUseCase.execute(id, updated);
        return result.map(s -> new ResponseEntity<>(new SesionDTO(s.getId(), s.getActividadId(), s.getFechaSesion(), s.getTitulo()), org.springframework.http.HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        // Elimina una sesión por id. Idempotente; devuelve 204.
        eliminarUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

