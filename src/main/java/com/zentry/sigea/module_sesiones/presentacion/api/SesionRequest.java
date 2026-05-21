package com.zentry.sigea.module_sesiones.presentacion.api;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para crear o actualizar una sesión
 */
public class SesionRequest {

    @NotBlank(message = "El ID de actividad es obligatorio")
    private String actividadId;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String titulo;

    private String descripcion;

    @NotNull(message = "La fecha de sesión es obligatoria")
    private LocalDateTime fecha_sesion;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime hora_inicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime hora_fin;

    private String ponente;

    @NotNull(message = "La modalidad es obligatoria")
    private Modalidad modalidad;

    private String lugar_sesion;

    private String link_virtual;

    private String orden;

    // Constructor vacío
    public SesionRequest() {}

    // Constructor completo
    public SesionRequest(String actividadId, String titulo, String descripcion, LocalDateTime fecha_sesion, LocalTime hora_inicio, LocalTime hora_fin, String ponente, Modalidad modalidad, String lugar_sesion, String link_virtual, String orden) {
        this.actividadId = actividadId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_sesion = fecha_sesion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.ponente = ponente;
        this.modalidad = modalidad;
        this.lugar_sesion = lugar_sesion;
        this.link_virtual = link_virtual;
        this.orden = orden;
    }

    // Getters y Setters
    public String getActividadId() { return actividadId; }
    public void setActividadId(String actividadId) { this.actividadId = actividadId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaSesion() { return fecha_sesion; }
    public void setFecha_sesion(LocalDateTime fecha_sesion) { this.fecha_sesion = fecha_sesion; }

    public LocalTime getHoraInicio() { return hora_inicio; }
    public void setHora_inicio(LocalTime hora_inicio) { this.hora_inicio = hora_inicio; }

    public LocalTime getHoraFin() { return hora_fin; }
    public void setHora_fin(LocalTime hora_fin) { this.hora_fin = hora_fin; }

    public String getPonente() { return ponente; }
    public void setPonente(String ponente) { this.ponente = ponente; }

    public Modalidad getModalidad() { return modalidad; }
    public void setModalidad(Modalidad modalidad) { this.modalidad = modalidad; }

    public String getLugarSesion() { return lugar_sesion; }
    public void setLugar_sesion(String lugar_sesion) { this.lugar_sesion = lugar_sesion; }

    public String getLinkVirtual() { return link_virtual; }
    public void setLink_virtual(String link_virtual) { this.link_virtual = link_virtual; }

    public String getOrden() { return orden; }
    public void setOrden(String orden) { this.orden = orden; }
}
