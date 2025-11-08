package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * DTO para crear una nueva sesión
 */
public class CrearSesionRequest {
    
    @NotNull(message = "El ID de actividad es obligatorio")
    @Positive(message = "El ID de actividad debe ser positivo")
    private UUID actividadId;
    
    @NotNull(message = "La fecha de sesión es obligatoria")
    private LocalDateTime fechaSesion;
    
    @NotNull(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String titulo;

    // Constructors
    public CrearSesionRequest() {}

    public CrearSesionRequest(UUID actividadId, LocalDateTime fechaSesion, String titulo) {
        this.actividadId = actividadId;
        this.fechaSesion = fechaSesion;
        this.titulo = titulo;
    }

    public UUID getActividadId() {
        return actividadId;
    }

    public void setActividadId(UUID actividadId) {
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