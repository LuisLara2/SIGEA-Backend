package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.GeneralResponseDTO;
import com.zentry.sigea.module_usuarios.services.EnviarCodigoVerificacionPorEmailService;
import com.zentry.sigea.module_usuarios.services.ValidarCodigoEnviadoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/usuarios/validar-correo")
@CrossOrigin(origins = "*")
public class ValidarCorreoApiRestController {
    
    private final EnviarCodigoVerificacionPorEmailService enviarCodigoVerificacionPorEmailService;
    private final ValidarCodigoEnviadoService validarCodigoEnviadoService;

    public ValidarCorreoApiRestController(
        EnviarCodigoVerificacionPorEmailService enviarCodigoVerificacionPorEmailService,
        ValidarCodigoEnviadoService validarCodigoEnviadoService
    ){
        this.enviarCodigoVerificacionPorEmailService = enviarCodigoVerificacionPorEmailService;
        this.validarCodigoEnviadoService = validarCodigoEnviadoService;
    }


    @PostMapping("/enviar-codigo-verificacion")
    @Operation(
        summary = "Enviar un codigo de verificacion a un correo especifico.",
        tags = {"Validar Correo"}
    )
    public ResponseEntity<GeneralResponseDTO<?>> enviarCodigoVerificacion(
        @RequestParam(name =  "correo") @NotNull(message = "Debe enviar el correo a verificar.") String correo , 
        @RequestParam(name = "nombres") @NotNull(message = "Debe enviar los nombres del usuario") String nombres
    ){
        try {
            enviarCodigoVerificacionPorEmailService.execute(correo, nombres);

            return ResponseEntity.status(HttpStatus.OK).body(
                new GeneralResponseDTO<>(
                    true, 
                    "Codigo de verificacion enviado al correo", 
                    null
                )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GeneralResponseDTO<>(
                    false, 
                    e.getMessage(), 
                    null
                )
            );
        }
    }

    @PostMapping("/validar-codigo-verificacion")
    @Operation(
        summary = "Enviar un codigo de verificacion para validarlo.",
        tags = {"Validar Correo"}
    )
    public ResponseEntity<GeneralResponseDTO<?>> validarCodigoEnviado(
        @RequestParam(name = "correo") @NotNull(message = "Debe especificar el correo para validar.") String correo,
        @RequestParam(name = "codigo") @NotNull(message = "Debe enviar el codigo de verificacion.") String codigo
    ){
        try {
            boolean resultadoValidacion = validarCodigoEnviadoService.execute(codigo, correo);

            if (resultadoValidacion) {
                return ResponseEntity.status(HttpStatus.OK).body(
                    new GeneralResponseDTO<>(
                        true, 
                        "Codigo validado con exito", 
                        null
                    )
                );                
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                    new GeneralResponseDTO<>(
                        false, 
                        "El codigo enviado es incorrecto", 
                        null
                    )
                );                
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GeneralResponseDTO<>(
                    false, 
                    e.getMessage(), 
                    null
                )
            );
        }
    }
}
