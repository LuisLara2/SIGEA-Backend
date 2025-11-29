package com.zentry.sigea.module_inscripciones.presentation.models.requestDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para crear/actualizar una inscripción
 */
public class InscripcionRequest {

    @NotNull(message = "Debe especificar una fecha de inscripcion.")
    private LocalDate fechaInscripcion;

    @NotBlank(message = "Debe especificar el estado de la inscripción.")
    private String estadoId;

    @NotBlank(message = "Debe especificar el ID del usuario.")
    private String usuarioId;

    @NotBlank(message = "Debe especificar el ID de la actividad.")
    private String actividadId;

    // Constructor vacío para Jackson
    public InscripcionRequest() {}

    public InscripcionRequest(LocalDate fechaInscripcion, String estadoId, String usuarioId, String actividadId) {
        this.fechaInscripcion = fechaInscripcion;
        this.estadoId = estadoId;
        this.usuarioId = usuarioId;
        this.actividadId = actividadId;
    }

    // Getters y Setters
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public String getEstadoId() {
        return estadoId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getActividadId() {
        return actividadId;
    }
}

