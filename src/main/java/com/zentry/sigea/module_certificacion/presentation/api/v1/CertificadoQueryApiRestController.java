package com.zentry.sigea.module_certificacion.presentation.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_certificacion.presentation.models.mappers.VerCertificadosParticipanteMapper;
import com.zentry.sigea.module_certificacion.presentation.models.responseDTO.GeneralResponseDTO;
import com.zentry.sigea.module_certificacion.presentation.models.responseDTO.VerCertificadosParticipanteResponseDTO;
import com.zentry.sigea.module_certificacion.services.CertificadoQueryService;
import com.zentry.sigea.security.UsuarioAuthInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/module_certificados")
public class CertificadoQueryApiRestController {
    
    private final CertificadoQueryService certificadoQueryService;

    public CertificadoQueryApiRestController(
        CertificadoQueryService certificadoQueryService
    ){
        this.certificadoQueryService = certificadoQueryService;
    }

    @GetMapping({"" , "ver-certificados"})
    @PreAuthorize("hasRole('ROLE_PARTICIPANTE')")
    @Operation(
        summary = "Obtener certificados de un participante",
        security = @SecurityRequirement(
            name = "participanteJWT"
            )
    )
    public ResponseEntity<GeneralResponseDTO<?>> verCertificadosParticipante(
        @AuthenticationPrincipal UsuarioAuthInfo usuarioAuthInfo
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                new GeneralResponseDTO<List<VerCertificadosParticipanteResponseDTO>>(
                    true, 
                    "Operacion exitosa", 
                    certificadoQueryService.verCertificadosParticipante(usuarioAuthInfo.getId()).stream()
                        .map(VerCertificadosParticipanteMapper::serviceToResponse)
                        .collect(Collectors.toList())
                )
            );
        } catch (RuntimeException r) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GeneralResponseDTO<>(
                    false, 
                    r.getMessage(), 
                    null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GeneralResponseDTO<>(
                    false, 
                    "Ocurrio un error al procesar la solicitud.", 
                    null)
            );
        }
    }
    

}
