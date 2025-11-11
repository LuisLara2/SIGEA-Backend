package com.zentry.sigea.module_inscripciones.presentation.models.requestDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para actualizar una inscripción
 */
public class InscripcionRequest {

    @NotNull(message = "Debe especificar una fecha de inscripcion.")
    private LocalDate fechaInscripcion;

    @NotBlank(message = "Debe especificar el estado de la actividad.")
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

    public String getEstadoId() {
        return estadoId;
    }
}

