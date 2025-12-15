package com.zentry.sigea.module_actividad.presentation.models.requestDTO;

import java.time.LocalDate;


/**
 * DTO para recibir datos de creación/actualización de actividad desde el frontend
 */
public class ActividadRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String coOrganizador;
    private String sponsor;
    private String estadoId;  // UUID del estado como String
    private String organizadorId;
    private String tipoActividadId;  // UUID del tipo de actividad como String
    private String ubicacion;
    private String bannerUrl;
    private String numeroYape;

    // Constructor vacío para Jackson
    public ActividadRequest() {}

    public ActividadRequest(
        String titulo, 
        String descripcion, 
        LocalDate fechaInicio, 
        LocalDate fechaFin, 
        String coOrganizador,
        String sponsor,
        String estadoId, 
        String organizadorId, 
        String tipoActividadId, 
        String ubicacion
    ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.coOrganizador = coOrganizador;
        this.sponsor = sponsor;
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

    public String getCoOrganizador() {
        return coOrganizador;
    }

    public void setCoOrganizador(String coOrganizador) {
        this.coOrganizador = coOrganizador;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getNumeroYape() {
        return numeroYape;
    }

    public void setNumeroYape(String numeroYape) {
        this.numeroYape = numeroYape;
    }
}