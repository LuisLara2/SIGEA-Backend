package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * DTO para crear una nueva sesión
 */
public class CrearSesionRequest {

    @NotBlank(message = "El ID de actividad es obligatorio")
    private String actividadId;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String titulo;

    private String descripcion;

    @NotNull(message = "La fecha de sesión es obligatoria")
    @JsonProperty("fecha_sesion")
    private LocalDateTime fechaSesion;

    @NotNull(message = "La hora de inicio es obligatoria")
    @JsonProperty("horaInicio") 
    @Schema(type = "string", format = "time", example = "10:00:00") 
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora_inicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @JsonProperty("horaFin") 
    @Schema(type = "string", format = "time", example = "12:00:00") 
    @JsonFormat(pattern = "HH:mm:ss") 
    private LocalTime hora_fin;

    private String ponente;

    @NotNull(message = "La modalidad es obligatoria")
    private Modalidad modalidad; 

    private String lugar_sesion;

    private String link_virtual;

    private String orden;

    // Constructors
    public CrearSesionRequest() {}

    public CrearSesionRequest(String actividadId, String titulo, String descripcion, LocalDateTime fecha_sesion, java.time.LocalTime hora_inicio, java.time.LocalTime hora_fin, String ponente, Modalidad modalidad, String lugar_sesion, String link_virtual, String orden) {
        this.actividadId = actividadId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaSesion = fechaSesion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.ponente = ponente;
        this.modalidad = modalidad;
        this.lugar_sesion = lugar_sesion;
        this.link_virtual = link_virtual;
        this.orden = orden;
    }

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
    
    public LocalDateTime getFechaSesion() {
        return fechaSesion;
    }
    public void setFechaSesion(LocalDateTime fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public java.time.LocalTime getHoraInicio() {
        return hora_inicio;
    }
    public void setHoraInicio(java.time.LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public java.time.LocalTime getHoraFin() {
        return hora_fin;
    }
    public void setHoraFin(java.time.LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getPonente() {
        return ponente;
    }
    public void setPonente(String ponente) {
        this.ponente = ponente;
    }

    public Modalidad getModalidad() {    
        return modalidad;
    }
    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public String getLugarSesion() {
        return lugar_sesion;
    }
    public void setLugarSesion(String lugar_sesion) {
        this.lugar_sesion = lugar_sesion;
    }

    public String getLinkVirtual() {
        return link_virtual;
    }
    public void setLink_virtual(String link_virtual) {
        this.link_virtual = link_virtual;
    }

    public String getOrden() {
        return orden;
    }
    public void setOrden(String orden) {
        this.orden = orden;
    }
}