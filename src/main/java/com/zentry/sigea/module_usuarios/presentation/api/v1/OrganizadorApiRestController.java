package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarAsistenciaRequestDTO;
import com.zentry.sigea.module_usuarios.services.AsistenciaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/usuarios/organizador")
public class OrganizadorApiRestController {
    
    private final AsistenciaService asistenciaService;

    public OrganizadorApiRestController(AsistenciaService asistenciaService){
        this.asistenciaService = asistenciaService;
    }

    @PostMapping("/registrar-asistencia")
    @PreAuthorize("hasRole('ORGANIZADOR')")
    public ResponseEntity<String> postMethodName(@RequestBody RegistrarAsistenciaRequestDTO registrarAsistenciaRequestDTO) {

        try {
            asistenciaService.registrarAsistencia(registrarAsistenciaRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Asistencia marcada con exito!.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }        
    }
}
