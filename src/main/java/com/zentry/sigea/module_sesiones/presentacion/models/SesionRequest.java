package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para actualizar una sesión existente
 */
public class SesionRequest {
    
    @NotNull(message = "La fecha de sesión es obligatoria")
    private LocalDateTime fechaSesion;
    
    @NotNull(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String titulo;

    public SesionRequest() {}

    public SesionRequest(LocalDateTime fechaSesion, String titulo) {
        this.fechaSesion = fechaSesion;
        this.titulo = titulo;
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