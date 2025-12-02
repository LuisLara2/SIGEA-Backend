package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;

/**
 * DTO de respuesta para sesiones
 */
public class SesionResponse {
    private String id;
    private String actividadId;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha_sesion;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private String ponente;
    private String lugar_sesion;
    private String link_virtual;
    private String orden;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SesionResponse() {}

    public SesionResponse(
        String id, 
        String actividadId, 
        String titulo,
        String descripcion,
        LocalDateTime fecha_sesion, 
        LocalTime hora_inicio,
        LocalTime hora_fin,
        String ponente,
        String lugar_sesion,
        String link_virtual,
        String orden,
        LocalDateTime createdAt, 
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.actividadId = actividadId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_sesion = fecha_sesion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.ponente = ponente;
        this.lugar_sesion = lugar_sesion;
        this.link_virtual = link_virtual;
        this.orden = orden;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Factory method para crear desde entidad de dominio
     */
    public static SesionResponse fromDomain(SesionDomainEntity domain) {
        return new SesionResponse(
            domain.getId(),
            domain.getActividadId(),
            domain.getTitulo(),
            domain.getDescripcion(),
            domain.getFechaSesion(),
            domain.getHoraInicio(),
            domain.getHoraFin(),
            domain.getPonente(),
            domain.getLugarSesion(),
            domain.getLinkVirtual(),
            domain.getOrden(),
            domain.getCreatedAt(),
            domain.getUpdatedAt()
        );
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return fecha_sesion;
    }

    public void setFechaSesion(LocalDateTime fecha_sesion) {
        this.fecha_sesion = fecha_sesion;
    }

    public LocalTime getHoraInicio() {
        return hora_inicio;
    }

    public void setHoraInicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHoraFin() {
        return hora_fin;
    }

    public void setHoraFin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getPonente() {
        return ponente;
    }

    public void setPonente(String ponente) {
        this.ponente = ponente;
    }
    
    public String getLugarSesion() {
        return lugar_sesion;
    }

    public void setLugarSesion(String lugarSesion) {
        this.lugar_sesion = lugarSesion;
    }

    public String getLinkVirtual() {
        return link_virtual;
    }

    public void setLinkVirtual(String link_virtual) {
        this.link_virtual = link_virtual;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}