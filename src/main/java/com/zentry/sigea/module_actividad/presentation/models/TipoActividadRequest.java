package com.zentry.sigea.module_actividad.presentation.models;

import java.time.LocalDateTime;

public class TipoActividadRequest {


    private final String nombreActividad;
    private final String descripcion;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TipoActividadRequest(String nombreActividad, String descripcion, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
