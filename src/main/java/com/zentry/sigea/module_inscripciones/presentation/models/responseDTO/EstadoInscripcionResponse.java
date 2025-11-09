package com.zentry.sigea.module_inscripciones.presentation.models.responseDTO;

import com.zentry.sigea.module_inscripciones.core.entities.EstadoInscripcionDomainEntity;

/**
 * DTO para enviar datos de estado de inscripción al frontend
 */
public class EstadoInscripcionResponse {
    private String codigo;
    private String etiqueta;

    // Constructor vacío para Jackson
    public EstadoInscripcionResponse() {}

    public EstadoInscripcionResponse(String codigo, String etiqueta) {
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }

    /**
     * Factory method para crear un EstadoInscripcionResponse desde una entidad EstadoInscripcion
     */
    public static EstadoInscripcionResponse fromEntity(EstadoInscripcionDomainEntity estadoInscripcionDomainEntity) {
        return new EstadoInscripcionResponse(
            estadoInscripcionDomainEntity.getCodigo(),
            estadoInscripcionDomainEntity.getEtiqueta()
        );
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

