package com.zentry.sigea.module_usuarios.presentation.api.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.LoginUsuarioRequestDTO;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.GeneralResponseDTO;
import com.zentry.sigea.module_usuarios.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class LoginUsuarioApiRestController {
    
    private final UsuarioService usuarioService;

    public LoginUsuarioApiRestController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }    

    @PostMapping("/auth/login")
    @Operation(
        summary = "Inicio de sesion de los usuarios"
    )
    public ResponseEntity<GeneralResponseDTO<?>> loginUsuario(
        @Valid @RequestBody LoginUsuarioRequestDTO loginUsuarioRequestDTO ,
        BindingResult bindingResult
    ) {
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            Map<String , String> errorMessages = new HashMap<>();

            bindingResult.getFieldErrors().forEach((errorField) -> {
                errorMessages.put(errorField.getField(), errorField.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GeneralResponseDTO<>(
                    false, 
                    "Error al iniciar sesion.", 
                    errorMessages
                )
            );
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                new GeneralResponseDTO<Map<String , String>>(
                    true , 
                    "Inicio de sesion exitoso", 
                    Map.of(
                        "tokenUsuario" , usuarioService.login(loginUsuarioRequestDTO.getCorreo(), loginUsuarioRequestDTO.getPassword()) 
                    )
                )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GeneralResponseDTO<>(
                    false, 
                    e.getMessage(), 
                    null
                )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GeneralResponseDTO<>(
                    false, 
                    "Ocurrio un error al procesar la solicitud.", 
                    null
                )
            );
        }
    }
}
