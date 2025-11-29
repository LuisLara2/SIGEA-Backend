package com.zentry.sigea.module_usuarios.presentation.api.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zentry.sigea.module_usuarios.presentation.models.mappers.CrearRolMapper;
import com.zentry.sigea.module_usuarios.presentation.models.mappers.RegistrarUsuarioMapper;
import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.CrearRolRequestDTO;
import com.zentry.sigea.module_usuarios.presentation.models.requestDTO.RegistrarUsuarioRequestDTO;
import com.zentry.sigea.module_usuarios.presentation.models.responseDTO.GeneralResponseDTO;
import com.zentry.sigea.module_usuarios.services.AdministradorService;
import com.zentry.sigea.security.UsuarioAuthInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/usuarios/administrador")
@CrossOrigin(origins = "*") // El frontend estara en "http://localhost:16000" en local
public class AdministradorApiRestController {
    
    private final AdministradorService administradorService;

    private static final Map<String, String> constraintMessages = Map.of(
        "uk_usuario_correo", "El correo ya está en uso.",
        "uk_usuario_dni", "El DNI ya está en uso.",
        "uk_telefono", "El telefono ya esta en uso.",
        "uk_nombre_rol", "El nombre de rol ya existe."
    );

    public AdministradorApiRestController(
        AdministradorService administradorService
    ){
        this.administradorService = administradorService;
    }

    @GetMapping("/home")
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @Operation(
        summary = "Home de administrador",
        security = @SecurityRequirement(
            name = "administradorJWT"
            ),
        tags = {"Home"}
    )
    public ResponseEntity<GeneralResponseDTO<?>> indexAdministrador(
        @AuthenticationPrincipal UsuarioAuthInfo usuarioAuthInfo
    ) {
        Map<String , String> dataForIndexAdministrador = new HashMap<>();

        dataForIndexAdministrador.put("usuarioId", usuarioAuthInfo.getId());
        dataForIndexAdministrador.put("correo", usuarioAuthInfo.getCorreo());

        return ResponseEntity.status(HttpStatus.OK).body(
            new GeneralResponseDTO<>(
                true, 
                "Operacion exitosa", 
                dataForIndexAdministrador
            )
        );
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @Operation(
        summary = "Crear usuario con cualquier rol",
        security = @SecurityRequirement(
            name = "administradorJWT"
            ),
        tags = {"Crear"}
    )
    public ResponseEntity<GeneralResponseDTO<?>> registerUsuario(
        @RequestBody RegistrarUsuarioRequestDTO registrarUsuarioRequestDTO
    ) {
        try {
            String registerMessage = administradorService.registerUsuario(
                RegistrarUsuarioMapper.requestToDomain(registrarUsuarioRequestDTO) , 
                registrarUsuarioRequestDTO.getRolId()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new GeneralResponseDTO<>(
                    true, 
                    registerMessage, 
                    null
                )
            );
        } catch (DataIntegrityViolationException ex) {

            Throwable causa = ex.getCause();
            while (causa != null && !(causa instanceof org.hibernate.exception.ConstraintViolationException)) {
                causa = causa.getCause();
            }

            if (causa instanceof org.hibernate.exception.ConstraintViolationException cve) {
                String constraint = cve.getConstraintName();

                if (constraint != null && constraintMessages.containsKey(constraint)) {

                    // Convertimos usuario_correo_key → correo
                    String field = constraint.replace("uk_usuario_", "");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new GeneralResponseDTO<>(
                            false, 
                            "Error de unicidad", 
                            Map.of(
                                "campo" , field , 
                                "mensaje", constraintMessages.get(constraint)
                            )
                        )
                    );
                }
            }

            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new GeneralResponseDTO<>(
                    false, 
                    ex.getMessage(), 
                    Map.of(
                    "Tipo de error", ex.getClass().getName() ,
                        "Mensaje del error" , ex.getMessage() , 
                        "Causa del error", ex.getCause()
                    )
                )
            );

        } catch (RuntimeException r) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new GeneralResponseDTO<>(
                    false, 
                    r.getMessage(), 
                    Map.of(
                    "Tipo de error", r.getClass().getName() ,
                        "Mensaje del error" , r.getMessage() , 
                        "Causa del error", r.getCause()
                    )
                )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GeneralResponseDTO<>(
                    false, 
                    "Ocurrio un error al procesar la solicitud.", 
                    Map.of(
                    "Tipo de error", e.getClass().getName() ,
                        "Mensaje del error" , e.getMessage() , 
                        "Causa del error", e.getCause()
                    )
                )
            );
        } 
    }
    
    @PostMapping("/crear-rol")
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @Operation(
        summary = "Crear roles",
        security = @SecurityRequirement(
            name = "administradorJWT"
            ),
        tags = {"Crear"}
    )
    public ResponseEntity<GeneralResponseDTO<?>> crearRol(
        @Valid @RequestBody CrearRolRequestDTO crearRolRequestDTO , 
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String , String> errorMeessages = new HashMap<>();

            bindingResult.getFieldErrors().forEach((errorField) -> {
                errorMeessages.put(errorField.getField(), errorField.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GeneralResponseDTO<>(
                    false , 
                    "Hay errores en los parametros" , 
                    errorMeessages
                )
            );
        }

        try {
            String crearRolMessage = administradorService.crearRol(
                CrearRolMapper.requestToDomain(crearRolRequestDTO)
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new GeneralResponseDTO<>(
                    true, 
                    crearRolMessage, 
                    null
                )
            );
        } catch (DataIntegrityViolationException ex) {

            Throwable causa = ex.getCause();
            while (causa != null && !(causa instanceof org.hibernate.exception.ConstraintViolationException)) {
                causa = causa.getCause();
            }

            if (causa instanceof org.hibernate.exception.ConstraintViolationException cve) {
                String constraint = cve.getConstraintName();

                if (constraint != null && constraintMessages.containsKey(constraint)) {

                    // Convertimos usuario_correo_key → correo
                    String field = constraint.replace("uk_", "");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new GeneralResponseDTO<>(
                            false, 
                            "Error de unicidad", 
                            Map.of(
                                "campo" , field , 
                                "mensaje", constraintMessages.get(constraint)
                            )
                        )
                    );
                }
            }

            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new GeneralResponseDTO<>(
                    false, 
                    ex.getMessage(), 
                    Map.of(
                    "Tipo de error", ex.getClass().getName() ,
                        "Mensaje del error" , ex.getMessage() , 
                        "Causa del error", ex.getCause()
                    )
                )
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GeneralResponseDTO<>(
                    false, 
                    "Ocurrio un error al procesar la solicitud", 
                    null
                )
            );
        }
    }    
}
