package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO de respuesta para sesiones
 */
@Getter
@Setter
public class SesionResponse {
    private String id;
    private String actividadId;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaSesion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String ponente;
    private String lugarSesion;
    private String linkVirtual;
    private String orden;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String modalidad;

    public SesionResponse() {}

    public SesionResponse(
        String id, 
        String actividadId, 
        String titulo,
        String descripcion,
        LocalDateTime fechaSesion, 
        LocalTime horaInicio,
        LocalTime horaFin,
        String ponente,
        String lugarSesion,
        String linkVirtual,
        String orden,
        LocalDateTime createdAt, 
        LocalDateTime updatedAt,
        String modalidad
    ) {
        this.id = id;
        this.actividadId = actividadId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaSesion = fechaSesion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ponente = ponente;
        this.lugarSesion = lugarSesion;
        this.linkVirtual = linkVirtual;
        this.orden = orden;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.modalidad = modalidad;
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
            domain.getUpdatedAt(),
            domain.getModalidad().name()
        );
    }
}