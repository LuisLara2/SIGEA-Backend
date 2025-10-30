package com.zentry.sigea.presentation.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.zentry.sigea.core.entities.actividad.Actividad;
import com.zentry.sigea.core.entities.actividad.TipoActividad;
import com.zentry.sigea.core.value_objects.EstadoActividad;

/**
 * DTO para enviar datos de actividad al frontend
 */
public class ActividadResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoActividad estado;
    private Long organizadorId;
    private TipoActividad tipoActividad;
    private String ubicacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    
    // Campos adicionales para la vista
    private boolean activa;
    private boolean finalizada;
    private boolean pendiente;
    private long duracionEnDias;

    // Constructor vacío para Jackson
    public ActividadResponse() {}

    public ActividadResponse(Long id, String titulo, String descripcion, LocalDate fechaInicio, 
                           LocalDate fechaFin, EstadoActividad estado, Long organizadorId, 
                           TipoActividad tipoActividad, String ubicacion, LocalDateTime fechaCreacion, 
                           LocalDateTime fechaActualizacion, boolean activa, boolean finalizada, 
                           boolean pendiente, long duracionEnDias) {
        this.id = id;
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
    public static ActividadResponse fromEntity(Actividad actividad) {
        return new ActividadResponse(
            actividad.getId(),
            actividad.getTitle(),
            actividad.getDescription(),
            actividad.getStartDate(),
            actividad.getEndDate(),
            actividad.getStatusId(),
            actividad.getOrganizerId(),
            actividad.getTypeId(),
            actividad.getLocation(),
            actividad.getCreatedAt(),
            actividad.getUpdatedAt(),
            actividad.isActive(),
            actividad.isFinished(),
            actividad.isPending(),
            actividad.getDurationInDays()
        );
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    public Long getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividad tipoActividad) {
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