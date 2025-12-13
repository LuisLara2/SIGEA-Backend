package com.zentry.sigea.module_sesiones.presentacion.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class SesionRequest {

    @JsonProperty("actividadId")
    @NotBlank(message = "El ID de la actividad es obligatorio")
    private String actividadId;

    @JsonProperty("titulo")
    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @JsonProperty("descripcion")
    private String descripcion;

    @JsonProperty("ponente")
    private String ponente;

    @JsonProperty("modalidad")
    @NotNull(message = "La modalidad es obligatoria")
    private String modalidad;

    @JsonProperty("linkVirtual")
    private String linkVirtual;

    @JsonProperty("orden")
    private String orden;

    @JsonProperty("fechaSesion")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime fechaSesion;

    @JsonProperty("lugarSesion")
    private String lugarSesion;

    @JsonProperty("horaInicio")
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "10:00:00") 
    private LocalTime horaInicio;

    @JsonProperty("horaFin")
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", format = "time", example = "12:00:00") 

    private LocalTime horaFin;

    // Getters y setters
    public String getActividadId() {
        return actividadId;
    }

    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

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

    public String getPonente() {
        return ponente;
    }

    public void setPonente(String ponente) {
        this.ponente = ponente;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getLinkVirtual() {
        return linkVirtual;
    }

    public void setLinkVirtual(String linkVirtual) {
        this.linkVirtual = linkVirtual;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public LocalDateTime getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(LocalDateTime fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public String getLugarSesion() {
        return lugarSesion;
    }

    public void setLugarSesion(String lugarSesion) {
        this.lugarSesion = lugarSesion;
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
}