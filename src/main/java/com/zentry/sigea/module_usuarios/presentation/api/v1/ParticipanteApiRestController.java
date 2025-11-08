package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarInscripcionRequestDTO;
import com.zentry.sigea.module_usuarios.services.InscripcionService;

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
    public ResponseEntity<String> registrarInscripcion(@RequestBody RegistrarInscripcionRequestDTO registrarInscripcionRequestDTO) {
        try {
            inscripcionService.registrarInscripcion(registrarInscripcionRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Inscripcion realizada con exito!.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
