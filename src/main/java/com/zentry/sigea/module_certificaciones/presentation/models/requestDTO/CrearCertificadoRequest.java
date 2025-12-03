package com.zentry.sigea.module_certificaciones.presentation.models.requestDTO;

import jakarta.validation.constraints.NotNull;

/**
 * DTO para solicitud de creación de certificado
 */
public class CrearCertificadoRequest {
    
    @NotNull(message = "El ID de inscripción es obligatorio")
    private String asistenciaId;
    
    private String observaciones;
    
    // Constructores
    public CrearCertificadoRequest() {}
    
    public CrearCertificadoRequest(String asistenciaId) {
        this.asistenciaId = asistenciaId;
    }
    
    // Getters y Setters
    public String getAsistenciaId() {
        return asistenciaId;
    }
    
    public void setAsistenciaId(String asistenciaId) {
        this.asistenciaId = asistenciaId;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}