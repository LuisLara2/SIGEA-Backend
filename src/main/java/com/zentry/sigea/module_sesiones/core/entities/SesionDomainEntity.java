package com.zentry.sigea.module_sesiones.core.entities;

import java.time.LocalDateTime;

/**
 * Entidad de dominio para Sesión
 * Representa una sesión dentro de una actividad
 */
public class SesionDomainEntity {
    private String id;
    private String actividadId;
    private LocalDateTime fechaSesion;
    private String titulo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor privado para forzar uso de factory methods
    private SesionDomainEntity() {}

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

    /* MÉTODOS DE FÁBRICA DEL DOMINIO */

    /**
     * Factory method para crear una nueva sesión
     */
    public static SesionDomainEntity create(
        String actividadId,  
        LocalDateTime fechaSesion,
        String titulo
    ) {
        validateCreationParams(actividadId, fechaSesion, titulo);
        
        LocalDateTime now = LocalDateTime.now();
        
        SesionDomainEntity sesion = new SesionDomainEntity();
        sesion.setActividadId(actividadId);
        sesion.setFechaSesion(fechaSesion);
        sesion.setTitulo(titulo);
        sesion.setCreatedAt(now);
        sesion.setUpdatedAt(now);
        
        return sesion;
    }

    /**
     * Factory method para reconstruir una sesión existente (desde DB)
     */
    public static SesionDomainEntity reconstruct(
        String id,
        String actividadId,
        LocalDateTime fechaSesion,
        String titulo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        SesionDomainEntity sesion = new SesionDomainEntity();
        sesion.setId(id);
        sesion.setActividadId(actividadId);
        sesion.setFechaSesion(fechaSesion);
        sesion.setTitulo(titulo);
        sesion.setCreatedAt(createdAt);
        sesion.setUpdatedAt(updatedAt);
        
        return sesion;
    }

    /* MÉTODOS DE NEGOCIO */

    /**
     * Actualiza la información de la sesión
     */
    public void updateInfo(LocalDateTime fechaSesion, String titulo) {
        validateUpdateParams(fechaSesion, titulo);
        
        this.fechaSesion = fechaSesion;
        this.titulo = titulo;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Reprograma la sesión a una nueva fecha
     */
    public void reprogramar(LocalDateTime nuevaFecha) {
        if (nuevaFecha == null) {
            throw new IllegalArgumentException("La nueva fecha no puede ser nula");
        }
        if (nuevaFecha.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se puede reprogramar a una fecha pasada");
        }
        
        this.fechaSesion = nuevaFecha;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Verifica si la sesión está en progreso
     */
    public boolean estaEnProgreso() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime finEstimado = fechaSesion.plusHours(2); // Asumiendo 2 horas de duración
        return now.isAfter(fechaSesion) && now.isBefore(finEstimado);
    }

    /**
     * Verifica si la sesión ya finalizó
     */
    public boolean yaFinalizo() {
        LocalDateTime finEstimado = fechaSesion.plusHours(2);
        return LocalDateTime.now().isAfter(finEstimado);
    }

    /**
     * Verifica si la sesión es próxima (dentro de las próximas 24 horas)
     */
    public boolean esProxima() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = ahora.plusHours(24);
        return fechaSesion.isAfter(ahora) && fechaSesion.isBefore(limite);
    }

    /* VALIDACIONES PRIVADAS */

    private static void validateCreationParams(String actividadId, LocalDateTime fechaSesion, String titulo) {
        if (actividadId == null) {
            throw new IllegalArgumentException("El ID de actividad debe ser un número positivo");
        }
        if (fechaSesion == null) {
            throw new IllegalArgumentException("La fecha de sesión no puede ser nula");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (titulo.length() > 150) {
            throw new IllegalArgumentException("El título no puede exceder 150 caracteres");
        }
    }

    private static void validateUpdateParams(LocalDateTime fechaSesion, String titulo) {
        if (fechaSesion == null) {
            throw new IllegalArgumentException("La fecha de sesión no puede ser nula");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (titulo.length() > 150) {
            throw new IllegalArgumentException("El título no puede exceder 150 caracteres");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SesionDomainEntity that = (SesionDomainEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SesionDomainEntity{" +
                "id=" + id +
                ", actividadId=" + actividadId +
                ", fechaSesion=" + fechaSesion +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}