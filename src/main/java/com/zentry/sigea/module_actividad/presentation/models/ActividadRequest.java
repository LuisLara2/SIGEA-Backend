package com.zentry.sigea.module_actividad.presentation.models;

import java.time.LocalDate;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;

/**
 * DTO para recibir datos de creación de actividad desde el frontend
 */
public class ActividadRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoActividad estado;
    private Long organizadorId;
    private TipoActividad tipoActividad;
    private String ubicacion;

    // Constructor vacío para Jackson
    public ActividadRequest() {}

    public ActividadRequest(String titulo, String descripcion, LocalDate fechaInicio, 
                          LocalDate fechaFin, EstadoActividad estado, Long organizadorId, 
                          TipoActividad tipoActividad, String ubicacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.organizadorId = organizadorId;
        this.tipoActividad = tipoActividad;
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

    public EstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    public Long getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}