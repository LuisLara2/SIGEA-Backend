package com.zentry.sigea.module_notificaciones.events.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Evento de dominio que representa la creación de una nueva sesión
 * Se publica cuando se programa una sesión para una actividad
 */
public class SesionCreadaEvent {
    
    private final String sesionId;
    private final String actividadId;
    private final String titulo;
    private final LocalDateTime fechaSesion;
    private final List<String> usuariosIds; // Usuarios inscritos en la actividad que deben ser notificados
    private final LocalDateTime fechaEvento;
    private final String lugar;
    private final String ponente;
    private final String modalidad;
    private final String linkVirtual;
    private final java.time.LocalTime horaInicio;
    private final java.time.LocalTime horaFin;
    private final String descripcion;
    
    public SesionCreadaEvent(
        String sesionId,
        String actividadId,
        String titulo,
        LocalDateTime fechaSesion,
        List<String> usuariosIds,
        LocalDateTime fechaEvento,
        String lugar,
        String ponente,
        String modalidad,
        String linkVirtual,
        java.time.LocalTime horaInicio,
        java.time.LocalTime horaFin,
        String descripcion
    ) {
        this.sesionId = sesionId;
        this.actividadId = actividadId;
        this.titulo = titulo;
        this.fechaSesion = fechaSesion;
        this.usuariosIds = usuariosIds;
        this.fechaEvento = fechaEvento;
        this.lugar = lugar;
        this.ponente = ponente;
        this.modalidad = modalidad;
        this.linkVirtual = linkVirtual;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.descripcion = descripcion;
    }

    public java.time.LocalTime getHoraInicio() {
        return horaInicio;
    }

    public java.time.LocalTime getHoraFin() {
        return horaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    // Getters
    public String getSesionId() {
        return sesionId;
    }
    
    public String getActividadId() {
        return actividadId;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public LocalDateTime getFechaSesion() {
        return fechaSesion;
    }
    
    public List<String> getUsuariosIds() {
        return usuariosIds;
    }
    
    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }

    public String getLugar() {
        return lugar;
    }

    public String getPonente() {
        return ponente;
    }

    public String getModalidad() {
        return modalidad;
    }

    public String getLinkVirtual() {
        return linkVirtual;
    }
}