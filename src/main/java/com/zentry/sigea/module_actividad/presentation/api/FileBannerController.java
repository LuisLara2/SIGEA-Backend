package com.zentry.sigea.module_actividad.presentation.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zentry.sigea.module_actividad.services.FileBannerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/actividad/banner")
@CrossOrigin(origins = "*")
public class FileBannerController {

    private final FileBannerService fileBannerService;

    public FileBannerController(FileBannerService fileBannerService) {
        this.fileBannerService = fileBannerService;
    }

    /**
     * Subir imagen de banner
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Subir imagen de banner",
        description = "Sube una imagen de banner para una actividad. Solo se permiten imágenes JPG, PNG, GIF o WEBP con un tamaño máximo de 30MB." ,
        security = @SecurityRequirement(
            name = "organizadorJWT"
        ),
        tags = {"Crear"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagen subida exitosamente"),
        @ApiResponse(responseCode = "400", description = "Archivo inválido o tipo no permitido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<BannerResponse> uploadBanner(
            @Parameter(
                description = "Archivo de imagen (JPG, PNG, GIF, WEBP). Máximo 10MB",
                required = true,
                content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            @RequestPart("imagen") MultipartFile imagen
    ) {
        try {
            String url = fileBannerService.uploadBanner(imagen);
            return ResponseEntity.ok(new BannerResponse(
                true,
                "Imagen subida exitosamente",
                url,
                Path.of(url).getFileName().toString()    
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new BannerResponse(
                false,
                e.getMessage(),
                null,
                null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BannerResponse(
                false,
                "Error al subir la imagen: " + e.getMessage(),
                null,
                null
            ));
        }
    }

    /**
     * Obtener imagen de banner
     */
    @GetMapping("/imagen/{filename}")
    @Operation(
        summary = "Obtener imagen de banner",
        description = "Retorna la imagen de banner por su nombre de archivo (UUID)",
        tags = {"Leer"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagen encontrada",
            content = @Content(mediaType = "image/*")),
        @ApiResponse(responseCode = "404", description = "Imagen no encontrada")
    })
    public ResponseEntity<byte[]> getBanner(
            @Parameter(description = "Nombre del archivo (UUID.extension)", required = true)
            @PathVariable("filename") String filename
    ) {
        try {
            Path imagePath = fileBannerService.getImagePath(filename);
            
            if (!Files.exists(imagePath)) {
                return ResponseEntity.notFound().build();
            }

            byte[] imageBytes = Files.readAllBytes(imagePath);
            String contentType = Files.probeContentType(imagePath);
            
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageBytes);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Eliminar imagen de banner
     */
    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ORGANIZADOR')")
    @Operation(
        summary = "Eliminar imagen de banner",
        description = "Elimina una imagen de banner existente usando su nombre de archivo",
        security = @SecurityRequirement(
            name = "organizadorJWT"
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagen eliminada exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<BannerResponse> deleteBanner(
            @Parameter(description = "Nombre del archivo o URL de la imagen a eliminar", required = true)
            @RequestParam("filename") String filename
    ) {
        try {
            fileBannerService.deleteBanner(filename);
            return ResponseEntity.ok(new BannerResponse(
                true,
                "Imagen eliminada exitosamente",
                null,
                null
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BannerResponse(
                false,
                "Error al eliminar la imagen: " + e.getMessage(),
                null,
                null
            ));
        }
    }

    /**
     * DTO de respuesta para operaciones de banner
     */
    public record BannerResponse(
        boolean success,
        String message,
        String url,
        String filename
    ) {}
}
