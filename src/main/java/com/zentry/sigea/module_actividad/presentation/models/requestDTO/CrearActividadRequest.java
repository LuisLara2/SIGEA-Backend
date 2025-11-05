package com.zentry.sigea.module_actividad.presentation.models.requestDTO;

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
    private String estadoId;
    private String organizadorId;
    private String tipoActividadId;
    private String ubicacion;

    // Constructor vacío para Jackson
    public CrearActividadRequest() {}

    public CrearActividadRequest(
        String titulo, 
        String descripcion, 
        LocalDate fechaInicio, 
        LocalDate fechaFin, 
        String estadoId, 
        String organizadorId, 
        String tipoActividadId, 
        String ubicacion
    ) {
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

    public String getEstadoId() {
        return estadoId;
    }
    public void setEstadoId(String estadoId) {
        this.estadoId = estadoId;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}