package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.CrearInscripcionRequest;
import com.zentry.sigea.module_inscripciones.services.InscripcionService;

@RestController
@RequestMapping("/api/v1/usuarios/participante")
@CrossOrigin(origins = "http://localhost:16000")
public class ParticipanteApiRestController {

    private final InscripcionService inscripcionService;

    public ParticipanteApiRestController(InscripcionService inscripcionService){
        this.inscripcionService = inscripcionService;
    }

    @PostMapping("/inscripcion")
    @PreAuthorize("hasRole('PARTICIPANTE')")
    public ResponseEntity<String> registrarInscripcion(@RequestBody CrearInscripcionRequest crearInscripcionRequest) {
        try {
            inscripcionService.crearInscripcion(crearInscripcionRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Inscripcion realizada con exito!.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
