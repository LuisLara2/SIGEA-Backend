package com.zentry.sigea.module_actividad.core.entities.actividad;

import java.time.LocalDateTime;

public class TipoActividad {
    private final Long id;
    private final String nombreActividad;
    private final String descripcion;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TipoActividad(Long id, String nombreActividad, String descripcion, LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        if (nombreActividad == null || nombreActividad.isBlank()) {
            throw new IllegalArgumentException("El nombre de la actividad no puede ser nulo o vacío");
        }
        this.id = id;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Long getId() {
        return id;
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
