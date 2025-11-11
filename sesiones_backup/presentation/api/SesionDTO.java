package com.zentry.sigea.modules.sesiones.presentation.api;

import java.time.LocalDateTime;

public class SesionDTO {
    private Long id;
    private Long actividadId;
    private LocalDateTime fechaSesion;
    private String titulo;

    public SesionDTO() {}

    public SesionDTO(Long id, Long actividadId, LocalDateTime fechaSesion, String titulo) {
        this.id = id;
        this.actividadId = actividadId;
        this.fechaSesion = fechaSesion;
        this.titulo = titulo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getActividadId() { return actividadId; }
    public void setActividadId(Long actividadId) { this.actividadId = actividadId; }

    public LocalDateTime getFechaSesion() { return fechaSesion; }
    public void setFechaSesion(LocalDateTime fechaSesion) { this.fechaSesion = fechaSesion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
}
