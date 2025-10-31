package com.zentry.sigea.module_actividad.presentation.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadResponse;
import com.zentry.sigea.module_actividad.services.TipoActividadService;



@RestController
@RequestMapping("/api/v1/tipos-actividad")
// cabecera para permitir solicitudes desde cualquier origen
@CrossOrigin(origins = "*")
public class TipoActividadController {

    private final TipoActividadService tipoActividadService;

    public TipoActividadController(TipoActividadService tipoActividadService) {
        this.tipoActividadService = tipoActividadService;
    }

    /**
     * Crear un nuevo tipo de actividad
     */

    @PostMapping("/create")
    public ResponseEntity<TipoActividadResponse>  createTipoActividad(@RequestBody TipoActividadRequest request) 
    {
        // Lógica para crear el tipo de actividad

        try {

            TipoActividad tipoActividadCreada = tipoActividadService.crearTipoActividad(request);
            TipoActividadResponse response = TipoActividadResponse.fromEntity(tipoActividadCreada);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    /*
     * Listar todos los tipos de actividad
    */
    @GetMapping
    public ResponseEntity<List<TipoActividadResponse>> listarTiposActividad() {
        List<TipoActividad> tiposActividad = tipoActividadService.listarTiposActividad();
        List<TipoActividadResponse> response = tiposActividad.stream()
            .map(TipoActividadResponse::fromEntity)
            .toList();
        return ResponseEntity.ok(response);
    }
    
}
