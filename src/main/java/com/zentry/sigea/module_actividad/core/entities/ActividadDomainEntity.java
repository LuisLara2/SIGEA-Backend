package com.zentry.sigea.module_actividad.core.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ActividadDomainEntity {
    private String actividadId;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String coOrganizador;
    private String sponsor;

    // Aqui no seria necesario llamar a una entidad, solo necesitaria esta info para poder operar
    private EstadoActividadDomainEntity estadoActividadDomainEntity;
    
    // Uso una relacion completa para que se pueda obtener mas informacion
    private String organizadorId;

    // Aqui tampoco seria necesario llamar a una entidad
    private TipoActividadDomainEntity tipoActividadDomainEntity;
    
    private String lugar;
    private String bannerUrl;
    private String numeroYape;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    public EstadoActividadDomainEntity getEstadoActividadDomainEntity() {
        return estadoActividadDomainEntity;
    }
    public void setEstadoActividadDomainEntity(EstadoActividadDomainEntity estadoActividadDomainEntity) {
        this.estadoActividadDomainEntity = estadoActividadDomainEntity;
    }

    public String getOrganizadorId() {
        return organizadorId;
    }
    public void setOrganizadorId(String organizadorId) {
        this.organizadorId = organizadorId;
    }
    
    public TipoActividadDomainEntity getTipoActividadDomainEntity() {
        return tipoActividadDomainEntity;
    }
    public void setTipoActividadDomainEntity(TipoActividadDomainEntity tipoActividadDomainEntity) {
        this.tipoActividadDomainEntity = tipoActividadDomainEntity;
    }

    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
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

    /* METODOS DEL DOMINIO */

    public String getActividadId() {
        return actividadId;
    }

    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

    public static ActividadDomainEntity create(
        String titulo, 
        String descripcion,
        LocalDate fechaInicio, 
        LocalDate fechaFin,
        LocalTime horaInicio,
        LocalTime horaFin,
        EstadoActividadDomainEntity estadoActividadDomainEntity, 
        String organizadorId,
        TipoActividadDomainEntity tipoActividadDomainEntity, 
        String lugar,
        String coOrganizador,
        String sponsor, 
        String bannerUrl,
        String numeroYape
    ) {
        
        LocalDateTime now = LocalDateTime.now();

        ActividadDomainEntity actividadDomainEntity = new ActividadDomainEntity();

        actividadDomainEntity.setTitulo(titulo);
        actividadDomainEntity.setDescripcion(descripcion);
        actividadDomainEntity.setFechaInicio(fechaInicio);
        actividadDomainEntity.setFechaFin(fechaFin);
        actividadDomainEntity.setHoraInicio(horaInicio);
        actividadDomainEntity.setHoraFin(horaFin);
        actividadDomainEntity.setEstadoActividadDomainEntity(estadoActividadDomainEntity);
        actividadDomainEntity.setOrganizadorId(organizadorId);
        actividadDomainEntity.setTipoActividadDomainEntity(tipoActividadDomainEntity);
        actividadDomainEntity.setLugar(lugar);
        actividadDomainEntity.setCoOrganizador(coOrganizador);
        actividadDomainEntity.setSponsor(sponsor);
        actividadDomainEntity.setBannerUrl(bannerUrl);
        actividadDomainEntity.setNumeroYape(numeroYape);
        actividadDomainEntity.setCreatedAt(now);
        actividadDomainEntity.setUpdatedAt(now);

        return actividadDomainEntity;
    }

    // Métodos de negocio - Actualización parcial (solo actualiza campos no nulos)
    public void updateInfo(
        String titulo, 
        String descripcion, 
        LocalDate fechaInicio, 
        LocalDate fechaFin,
        LocalTime horaInicio,
        LocalTime horaFin,
        String lugar,
        String coOrganizador,
        String sponsor,
        String bannerUrl,
        String numeroYape
    ) {
        // Solo actualiza si el valor no es null
        if (titulo != null) this.titulo = titulo;
        if (descripcion != null) this.descripcion = descripcion;
        if (fechaInicio != null) this.fechaInicio = fechaInicio;
        if (fechaFin != null) this.fechaFin = fechaFin;
        if (horaInicio != null) this.horaInicio = horaInicio;
        if (horaFin != null) this.horaFin = horaFin;
        if (lugar != null) this.lugar = lugar;
        if (coOrganizador != null) this.coOrganizador = coOrganizador;
        if (sponsor != null) this.sponsor = sponsor;
        if (bannerUrl != null) this.bannerUrl = bannerUrl;
        if (numeroYape != null) this.numeroYape = numeroYape;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeStatus(EstadoActividadDomainEntity estadoActividadDomainEntity) {
        if (estadoActividadDomainEntity == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        this.estadoActividadDomainEntity = estadoActividadDomainEntity;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(fechaInicio) && !today.isAfter(fechaFin);
    }

    public boolean isFinished() {
        return LocalDate.now().isAfter(fechaFin);
    }

    public boolean isPending() {
        return LocalDate.now().isBefore(fechaInicio);
    }

    public long getDurationInDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
    }

    public String getCoOrganizador() {
        return coOrganizador;
    }

    public void setCoOrganizador(String coOrganizador) {
        this.coOrganizador = coOrganizador;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

}