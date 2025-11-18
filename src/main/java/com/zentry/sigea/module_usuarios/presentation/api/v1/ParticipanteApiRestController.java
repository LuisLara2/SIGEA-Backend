package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_inscripciones.presentation.models.mappers.CrearInscripcionMapper;
import com.zentry.sigea.module_inscripciones.presentation.models.requestDTO.CrearInscripcionRequest;
import com.zentry.sigea.module_inscripciones.services.InscripcionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/usuarios/participante")
@CrossOrigin(origins = "*")
public class ParticipanteApiRestController {

    private final InscripcionService inscripcionService;

    public ParticipanteApiRestController(InscripcionService inscripcionService){
        this.inscripcionService = inscripcionService;
    }

    @PostMapping("/inscripcion")
    @PreAuthorize("hasRole('ROLE_PARTICIPANTE')")
    @Operation(
        summary = "Registrar inscripcion en un evento.",
        security = @SecurityRequirement(
            name = "participanteJWT"
            )
    )
    public ResponseEntity<String> registrarInscripcion(@RequestBody CrearInscripcionRequest crearInscripcionRequest) {
        try {
            inscripcionService.crearInscripcion(
                CrearInscripcionMapper.requestToService(crearInscripcionRequest)
            );
            return ResponseEntity.status(HttpStatus.OK).body("Inscripcion realizada con exito!.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*
    El nombre del esquema en Swagger no afecta la validación de JWT, solo la UI y documentación.
    Puedes combinar varios esquemas en un mismo endpoint si quieres que acepte cualquiera:
    
    @Operation(security = {
        @SecurityRequirement(name = "adminJWT"),
        @SecurityRequirement(name = "userJWT")
    })

    // Esto significa “el cliente puede enviar cualquiera de los dos tokens y será aceptado”.
    */

}
