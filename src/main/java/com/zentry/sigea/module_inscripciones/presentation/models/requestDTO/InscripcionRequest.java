package com.zentry.sigea.module_inscripciones.presentation.models.requestDTO;

import java.time.LocalDate;

/**
 * DTO para actualizar una inscripción
 */
public class InscripcionRequest {
    private LocalDate fechaInscripcion;
    private String estadoId;

    // Constructor vacío para Jackson
    public InscripcionRequest() {}

    public InscripcionRequest(LocalDate fechaInscripcion, String estadoId) {
        this.fechaInscripcion = fechaInscripcion;
        this.estadoId = estadoId;
    }

    // Getters y Setters
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
    }
}

