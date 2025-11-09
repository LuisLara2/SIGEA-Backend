package com.zentry.sigea.module_inscripciones.presentation.models.requestDTO;

import java.time.LocalDate;

/**
 * DTO para recibir datos de creación de inscripción desde el frontend
 */
public class CrearInscripcionRequest {
    private LocalDate fechaInscripcion;
    private String usuarioId;
    private String actividadId;
    private String estadoId;

    // Constructor vacío para Jackson
    public CrearInscripcionRequest() {}

    public CrearInscripcionRequest(
        LocalDate fechaInscripcion,
        String usuarioId,
        String actividadId,
        String estadoId
    ) {
        this.fechaInscripcion = fechaInscripcion;
        this.usuarioId = usuarioId;
        this.actividadId = actividadId;
        this.estadoId = estadoId;
    }

    // Getters y Setters
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getActividadId() {
        return actividadId;
    }

    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }
}

