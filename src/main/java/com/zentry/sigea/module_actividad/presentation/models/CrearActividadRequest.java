package com.zentry.sigea.module_actividad.presentation.models;

import java.time.LocalDate;

/**
 * DTO para recibir datos de creación de actividad desde el frontend
 * Recibe solo los IDs de estado y tipo de actividad
 */
public class CrearActividadRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long estadoId;
    private Long organizadorId;
    private Long tipoActividadId;
    private String ubicacion;

    // Constructor vacío para Jackson
    public CrearActividadRequest() {}

    public CrearActividadRequest(String titulo, String descripcion, LocalDate fechaInicio, 
                               LocalDate fechaFin, Long estadoId, Long organizadorId, 
                               Long tipoActividadId, String ubicacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoId = estadoId;
        this.organizadorId = organizadorId;
        this.tipoActividadId = tipoActividadId;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
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

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Long getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

    public Long getTipoActividadId() {
        return tipoActividadId;
    }

    public void setTipoActividadId(Long tipoActividadId) {
        this.tipoActividadId = tipoActividadId;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}