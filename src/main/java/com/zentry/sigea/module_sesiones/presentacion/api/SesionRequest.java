package com.zentry.sigea.module_sesiones.presentacion.api;

import java.time.LocalDateTime;

public class SesionRequest {
    private Long actividadId;
    private LocalDateTime fechaSesion;
    private String titulo;

    public SesionRequest() {}

    public Long getActividadId() { return actividadId; }
    public void setActividadId(Long actividadId) { this.actividadId = actividadId; }

    public LocalDateTime getFechaSesion() { return fechaSesion; }
    public void setFechaSesion(LocalDateTime fechaSesion) { this.fechaSesion = fechaSesion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
}
