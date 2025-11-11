package com.zentry.sigea.modules.sesiones.presentation.models;

import java.time.LocalDateTime;

public class SesionDTO {
    private Long id;
    private String titulo;
    private LocalDateTime fechaInicio;

    public SesionDTO() {}

    public SesionDTO(Long id, String titulo, LocalDateTime fechaInicio) {
        this.id = id;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
}
