package com.zentry.sigea.module_inscripciones.presentation.models.requestDTO;

/**
 * DTO para crear o actualizar un estado de inscripción
 */
public class EstadoInscripcionRequest {
    private String codigo;
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

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}

