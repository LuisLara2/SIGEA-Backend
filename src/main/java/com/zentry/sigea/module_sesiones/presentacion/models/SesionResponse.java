package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;

/**
 * DTO de respuesta para sesiones
 */
public class SesionResponse {
    private String id;
    private String actividadId;
    private LocalDateTime fechaSesion;
    private String titulo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SesionResponse() {}

    public SesionResponse(
        String id, 
        String actividadId, 
        LocalDateTime fechaSesion, 
        String titulo,
        LocalDateTime createdAt, 
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.actividadId = actividadId;
        this.fechaSesion = fechaSesion;
        this.titulo = titulo;
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
            domain.getFechaSesion(),
            domain.getTitulo(),
            domain.getCreatedAt(),
            domain.getUpdatedAt()
        );
    }

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

    public LocalDateTime getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(LocalDateTime fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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