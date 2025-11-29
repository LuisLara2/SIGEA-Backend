package com.zentry.sigea.module_actividad.presentation.models.responseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para enviar datos de actividad al frontend
 */
public class ActividadResponse {
    private String id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    @Schema(type = "string", pattern = "HH:mm", example = "09:00", description = "Hora de inicio en formato HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaInicio;
    
    @Schema(type = "string", pattern = "HH:mm", example = "17:00", description = "Hora de fin en formato HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaFin;
    private EstadoActividadResponse estado;
    private String organizadorId;
    private TipoActividadResponse tipoActividad;
    private String ubicacion;
    private String coOrganizador;
    private String sponsor;
    private String bannerUrl;
    private String numeroYape;
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
        String id,
        String titulo, 
        String descripcion, 
        LocalDate fechaInicio, 
        LocalDate fechaFin,
        LocalTime horaInicio,
        LocalTime horaFin,
        EstadoActividadResponse estado, 
        String organizadorId, 
        TipoActividadResponse tipoActividad, 
        String ubicacion,
        String coOrganizador,
        String sponsor,
        String bannerUrl,
        String numeroYape,
        LocalDateTime fechaCreacion, 
        LocalDateTime fechaActualizacion, 
        boolean activa, 
        boolean finalizada, 
        boolean pendiente, 
        Long duracionEnDias
    ) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.organizadorId = organizadorId;
        this.tipoActividad = tipoActividad;
        this.ubicacion = ubicacion;
        this.coOrganizador = coOrganizador;
        this.sponsor = sponsor;
        this.bannerUrl = bannerUrl;
        this.numeroYape = numeroYape;
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
            actividadDomainEntity.getActividadId(),
            actividadDomainEntity.getTitulo(),
            actividadDomainEntity.getDescripcion(),
            actividadDomainEntity.getFechaInicio(),
            actividadDomainEntity.getFechaFin(),
            actividadDomainEntity.getHoraInicio(),
            actividadDomainEntity.getHoraFin(),
            EstadoActividadResponse.fromEntity(actividadDomainEntity.getEstadoActividadDomainEntity()),
            actividadDomainEntity.getOrganizadorId(),
            TipoActividadResponse.fromEntity(actividadDomainEntity.getTipoActividadDomainEntity()),
            actividadDomainEntity.getLugar(),
            actividadDomainEntity.getCoOrganizador(),
            actividadDomainEntity.getSponsor(),
            actividadDomainEntity.getBannerUrl(),
            actividadDomainEntity.getNumeroYape(),
            actividadDomainEntity.getCreatedAt(),
            actividadDomainEntity.getUpdatedAt(),
            actividadDomainEntity.isActive(),
            actividadDomainEntity.isFinished(),
            actividadDomainEntity.isPending(),
            actividadDomainEntity.getDurationInDays()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public EstadoActividadResponse getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividadResponse estado) {
        this.estado = estado;
    }

    public String getOrganizadorId() {
        return organizadorId;
    }
    public void setOrganizadorId(String organizadorId) {
        this.organizadorId = organizadorId;
    }

    public TipoActividadResponse getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(TipoActividadResponse tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getCoOrganizador() {
        return coOrganizador;
    }

    public void setCoOrganizador(String coOrganizador) {
        this.coOrganizador = coOrganizador;
    }
}