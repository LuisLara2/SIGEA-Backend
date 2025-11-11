package com.zentry.sigea.modules.sesiones.presentation.models;

import java.time.LocalDateTime;

public class SesionRequest {
    private String titulo;
    private LocalDateTime fechaInicio;

    public SesionRequest() {}

    public SesionRequest(String titulo, LocalDateTime fechaInicio) {
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
}
