package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para crear una nueva sesión
 */
public class CrearSesionRequest {
    
    @NotBlank(message = "El ID de actividad es obligatorio")
    private String actividadId;
    
    @NotNull(message = "La fecha de sesión es obligatoria")
    private LocalDateTime fechaSesion;
    
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String titulo;

    // Constructors
    public CrearSesionRequest() {}

    public CrearSesionRequest(String actividadId, LocalDateTime fechaSesion, String titulo) {
        this.actividadId = actividadId;
        this.fechaSesion = fechaSesion;
        this.titulo = titulo;
    }

    public String getActividadId() {
        return actividadId;
    }
    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

    public LocalDateTime getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(LocalDateTime fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}