package com.zentry.sigea.module_actividad.presentation.models.requestDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para recibir datos de creación de actividad desde el frontend
 * Recibe solo los IDs de estado y tipo de actividad
 */
public class CrearActividadRequest {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    @Schema(type = "string", pattern = "HH:mm", example = "09:00", description = "Hora de inicio en formato HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaInicio;
    
    @Schema(type = "string", pattern = "HH:mm", example = "18:00", description = "Hora de fin en formato HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFin;
    
    private String estadoId;
    private String organizadorId;
    private String tipoActividadId;
    private String ubicacion;
    private String bannerUrl;
    private String numeroYape;

    // Constructor vacío para Jackson
    public CrearActividadRequest() {}

    public CrearActividadRequest(
        String titulo, 
        String descripcion, 
        LocalDate fechaInicio, 
        LocalDate fechaFin,
        LocalTime horaInicio,
        LocalTime horaFin,
        String estadoId, 
        String organizadorId, 
        String tipoActividadId, 
        String ubicacion,
        String bannerUrl,
        String numeroYape
    ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estadoId = estadoId;
        this.organizadorId = organizadorId;
        this.tipoActividadId = tipoActividadId;
        this.ubicacion = ubicacion;
        this.bannerUrl = bannerUrl;
        this.numeroYape = numeroYape;
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
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