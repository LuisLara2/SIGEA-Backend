package com.zentry.sigea.module_actividad.presentation.models.requestDTO;

import java.time.LocalDate;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;

/**
 * DTO para recibir datos de creación de actividad desde el frontend
 */
public class ActividadRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoActividadDomainEntity estado;
    private String organizadorId;
    private TipoActividadDomainEntity tipoActividad;
    private String ubicacion;

    // Constructor vacío para Jackson
    public ActividadRequest() {}

    public ActividadRequest(
        String titulo, 
        String descripcion, 
        LocalDate fechaInicio, 
        LocalDate fechaFin, 
        EstadoActividadDomainEntity estado, 
        String organizadorId, 
        TipoActividadDomainEntity tipoActividad, 
        String ubicacion
    ) {
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

    public EstadoActividadDomainEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividadDomainEntity estado) {
        this.estado = estado;
    }

    public String getOrganizadorId() {
        return organizadorId;
    }
    public void setOrganizadorId(String organizadorId) {
        this.organizadorId = organizadorId;
    }

    public TipoActividadDomainEntity getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividadDomainEntity tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}