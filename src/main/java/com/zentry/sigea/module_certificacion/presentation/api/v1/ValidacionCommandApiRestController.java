package com.zentry.sigea.module_certificacion.presentation.api.v1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_certificacion.presentation.models.mappers.CrearValidacionMapper;
import com.zentry.sigea.module_certificacion.presentation.models.requestDTO.CrearValidacionRequestDTO;
import com.zentry.sigea.module_certificacion.presentation.models.responseDTO.GeneralResponseDTO;
import com.zentry.sigea.module_certificacion.services.ValidacionCommandService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/module_certificados")
@CrossOrigin(origins = "*")
public class ValidacionCommandApiRestController {
    
    private final ValidacionCommandService validacionCommandService;

    public ValidacionCommandApiRestController(
        ValidacionCommandService validacionCommandService
    ){
        this.validacionCommandService = validacionCommandService;
    }

    @PostMapping("/crear-validacion")
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @Operation(
        summary = "Crear validaciones para un certificado",
        security = @SecurityRequirement(
            name = "administradorJWT"
            )
    )
    public ResponseEntity<GeneralResponseDTO<?>> crearValidacion(
        @RequestBody List<CrearValidacionRequestDTO> crearValidacionRequestDTO
    ){
        try {
            String crearValidacionMessage = validacionCommandService.crearValidacion(
                crearValidacionRequestDTO.stream()
                    .map(CrearValidacionMapper::requestToService)
                    .collect(Collectors.toList())
            );;

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new GeneralResponseDTO<>(
                    true, crearValidacionMessage, 
                    null
                )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GeneralResponseDTO<>(
                    false, 
                    "Ocurrio un error en la peticion", 
                    null
                )
            );
        }
    }

}
