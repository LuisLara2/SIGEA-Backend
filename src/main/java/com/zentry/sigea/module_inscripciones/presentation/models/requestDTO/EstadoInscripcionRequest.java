package com.zentry.sigea.module_inscripciones.presentation.models.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para crear o actualizar un estado de inscripción
 */
public class EstadoInscripcionRequest {

    @NotBlank(message = "Debe especificar el codigo del estado.")
    @Size(max = 30 , message = "El codigo no debe tener más de 30 caracteres.")
    private String codigo;

    @Size(max = 60 , message = "La etiqueta no debe tener más de 60 caracteres.")
    private String etiqueta;

    // Constructor vacío para Jackson
    public EstadoInscripcionRequest() {}

    public EstadoInscripcionRequest(String codigo, String etiqueta) {
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}

