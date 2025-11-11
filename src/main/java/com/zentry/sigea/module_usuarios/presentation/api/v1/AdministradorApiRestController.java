package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/usuarios/administrador")
@CrossOrigin(origins = "*") // El frontend estara en "http://localhost:16000" en local
public class AdministradorApiRestController {

    @GetMapping("/home")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<String> home() {
        return ResponseEntity.status(HttpStatus.OK).body("HELLO");
    }
}
