package com.zentry.sigea.module_actividad.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class FileBannerService {

    @Value("${server.port:16001}")
    private String serverPort;

    // Carpeta donde se guardan los banners
    private static final String UPLOAD_DIR = "uploads/banners";

    // Tipos de imagen permitidos
    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
        "image/jpeg",
        "image/jpg", 
        "image/png",
        "image/gif",
        "image/webp"
    );

    // Extensiones permitidas
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
        ".jpg", ".jpeg", ".png", ".gif", ".webp"
    );

    // Tamaño máximo: 30MB
    private static final long MAX_FILE_SIZE = 30 * 1024 * 1024;

    /**
     * Crear la carpeta de uploads al iniciar el servicio
     */
    @PostConstruct
    public void init() {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear la carpeta de uploads: " + e.getMessage(), e);
        }
    }

    /**
     * Sube una imagen de banner a la carpeta local
     * @param file Archivo de imagen
     * @return URL pública de la imagen
     */
    public String uploadBanner(MultipartFile file) {
        // Validar que sea una imagen válida
        validateImage(file);

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            
            Path filePath = Paths.get(UPLOAD_DIR, uniqueFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Retornar la URL para acceder a la imagen
            return "/api/v1/actividad/banner/imagen/" + uniqueFileName;

        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene la ruta del archivo de imagen
     * @param filename Nombre del archivo
     * @return Path del archivo
     */
    public Path getImagePath(String filename) {
        return Paths.get(UPLOAD_DIR, filename);
    }

    /**
     * Elimina un banner local
     * @param filename Nombre del archivo a eliminar
     */
    public void deleteBanner(String filename) {
        if (filename == null || filename.isEmpty()) {
            return;
        }

        try {
            // Extraer solo el nombre del archivo si viene la URL completa
            if (filename.contains("/")) {
                filename = filename.substring(filename.lastIndexOf("/") + 1);
            }
            
            Path filePath = Paths.get(UPLOAD_DIR, filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar la imagen: " + e.getMessage(), e);
        }
    }

    /**
     * Verifica si un archivo existe
     */
    public boolean existsBanner(String filename) {
        Path filePath = Paths.get(UPLOAD_DIR, filename);
        return Files.exists(filePath);
    }

    /**
     * Valida que el archivo sea una imagen válida
     */
    private void validateImage(MultipartFile file) {
        // Validar que no esté vacío
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo no puede estar vacío");
        }

        // Validar tipo de contenido
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase())) {
            throw new IllegalArgumentException(
                "Tipo de archivo no permitido. Solo se permiten imágenes: JPG, PNG, GIF, WEBP"
            );
        }

        // Validar extensión
        String filename = file.getOriginalFilename();
        if (filename != null) {
            String extension = getFileExtension(filename).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                throw new IllegalArgumentException(
                    "Extensión de archivo no permitida. Solo se permiten: .jpg, .jpeg, .png, .gif, .webp"
                );
            }
        }

        // Validar tamaño
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                "La imagen es demasiado grande. Tamaño máximo permitido: 5MB"
            );
        }
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }
}
