package com.zentry.sigea.modules.sesiones.core.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad de dominio Sesion para el módulo sesiones.
 * Alineada con la entidad persistente: id, actividadId, fechaSesion, titulo.
 */
public class Sesion {
    private Long id;
    private Long actividadId;
    private LocalDateTime fechaSesion;
    private String titulo;

    private Sesion(Long id, Long actividadId, LocalDateTime fechaSesion, String titulo) {
        this.id = id;
        this.actividadId = actividadId;
        this.fechaSesion = fechaSesion;
        this.titulo = titulo;
    }

    public static Sesion create(Long actividadId, LocalDateTime fechaSesion, String titulo) {
        if (actividadId == null) throw new IllegalArgumentException("actividadId es obligatorio");
        if (fechaSesion == null) throw new IllegalArgumentException("fechaSesion es obligatorio");
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("titulo es obligatorio");
        return new Sesion(null, actividadId, fechaSesion, titulo.trim());
    }

    public static Sesion withId(Long id, Long actividadId, LocalDateTime fechaSesion, String titulo) {
        Objects.requireNonNull(id, "id no puede ser null");
        return new Sesion(id, actividadId, fechaSesion, titulo);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getActividadId() { return actividadId; }
    public void setActividadId(Long actividadId) { this.actividadId = actividadId; }

    public LocalDateTime getFechaSesion() { return fechaSesion; }
    public void setFechaSesion(LocalDateTime fechaSesion) { this.fechaSesion = fechaSesion; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sesion sesion = (Sesion) o;
        return Objects.equals(id, sesion.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Sesion{" + "id=" + id + ", actividadId=" + actividadId + ", fechaSesion=" + fechaSesion + ", titulo='" + titulo + '\'' + '}';
    }
}




