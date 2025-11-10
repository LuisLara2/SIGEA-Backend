package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.LoginUsuarioRequestDTO;
import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarUsuarioRequestDTO;
import com.zentry.sigea.module_usuarios.services.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/usuarios/auth")
public class AuthApiRestController {
    
    private final AuthService authService;

    public AuthApiRestController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
        @RequestBody RegistrarUsuarioRequestDTO registrarUsuarioRequestDTO
    ) {
        try {
            String registerMessage = authService.register(registrarUsuarioRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(registerMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public String login(
        @RequestBody LoginUsuarioRequestDTO loginUsuarioRequestDTO
    ) {
        return authService.login(loginUsuarioRequestDTO.getCorreo(), loginUsuarioRequestDTO.getPassword());
    }
    
}
