package com.zentry.sigea.module_inscripciones.presentation.models.requestDTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para recibir datos de creación de inscripción desde el frontend
 */
public class CrearInscripcionRequest {

    // Notnull es para Integer LocalDate o LodalDateTime
    @NotNull(message = "Debe especificar una fecha de inscripcion.")
    private LocalDate fechaInscripcion;

    // NotBlank es para valores String, Char, etc
    @NotBlank(message = "Debe especificar el usuario a inscribirse.")
    private String usuarioId;

    @NotBlank(message = "Debe especificar la actividad a la cual se inscribira.")
    private String actividadId;

    @NotBlank(message = "Debe especificar el estado de la actividad a inscribirse.")
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

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getActividadId() {
        return actividadId;
    }

    public String getEstadoId() {
        return estadoId;
    }
}

