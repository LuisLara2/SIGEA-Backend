package com.zentry.sigea.module_actividad.presentation.models.responseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;

/**
 * DTO para enviar datos de actividad al frontend
 */
public class ActividadResponse {
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoActividadDomainEntity estado;
    private String organizadorId;
    private TipoActividadDomainEntity tipoActividad;
    private String ubicacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    
    // Campos adicionales para la vista
    private boolean activa;
    private boolean finalizada;
    private boolean pendiente;
    private Long duracionEnDias;

    // Constructor vacío para Jackson
    public ActividadResponse() {}

    public ActividadResponse(
        String titulo, 
        String descripcion, 
        LocalDate fechaInicio, 
        LocalDate fechaFin, 
        EstadoActividadDomainEntity estado, 
        String organizadorId, 
        TipoActividadDomainEntity tipoActividad, 
        String ubicacion, 
        LocalDateTime fechaCreacion, 
        LocalDateTime fechaActualizacion, 
        boolean activa, 
        boolean finalizada, 
        boolean pendiente, 
        Long duracionEnDias
    ) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.organizadorId = organizadorId;
        this.tipoActividad = tipoActividad;
        this.ubicacion = ubicacion;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.activa = activa;
        this.finalizada = finalizada;
        this.pendiente = pendiente;
        this.duracionEnDias = duracionEnDias;
    }

    /**
     * Factory method para crear un ActividadResponse desde una entidad Actividad
     */
    public static ActividadResponse fromEntity(ActividadDomainEntity actividadDomainEntity) {
        return new ActividadResponse(
            actividadDomainEntity.getTitulo(),
            actividadDomainEntity.getDescripcion(),
            actividadDomainEntity.getFechaInicio(),
            actividadDomainEntity.getFechaFin(),
            actividadDomainEntity.getEstadoActividadDomainEntity(),
            actividadDomainEntity.getOrganizadorId(),
            actividadDomainEntity.getTipoActividadDomainEntity(),
            actividadDomainEntity.getLugar(),
            actividadDomainEntity.getCreatedAt(),
            actividadDomainEntity.getUpdatedAt(),
            actividadDomainEntity.isActive(),
            actividadDomainEntity.isFinished(),
            actividadDomainEntity.isPending(),
            actividadDomainEntity.getDurationInDays()
        );
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }

    public long getDuracionEnDias() {
        return duracionEnDias;
    }

    public void setDuracionEnDias(long duracionEnDias) {
        this.duracionEnDias = duracionEnDias;
    }
}