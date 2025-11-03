package com.zentry.sigea.core.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActividadDomainEntity {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    // Aqui no seria necesario llamar a una entidad, solo necesitaria esta info para poder operar
    private String estadoActividadId;
    
    // Uso una relacion completa para que se pueda obtener mas informacion
    private String organizadorId;

    // Aqui tampoco seria necesario llamar a una entidad
    private String tipoActividadId;
    
    private String lugar;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstadoActividadId() {
        return estadoActividadId;
    }
    public void setEstadoActividadId(String estadoActividadId) {
        this.estadoActividadId = estadoActividadId;
    }

    public String getOrganizadorId() {
        return organizadorId;
    }
    public void setOrganizadorId(String organizadorId) {
        this.organizadorId = organizadorId;
    }
    
    public String getTipoActividadId() {
        return tipoActividadId;
    }
    public void setTipoActividadId(String tipoActividadId) {
        this.tipoActividadId = tipoActividadId;
    }

    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
