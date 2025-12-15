package com.zentry.sigea.module_sesiones.core.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;
/**
 * Entidad de dominio para Sesión
 * Representa una sesión dentro de una actividad
 */
public class SesionDomainEntity {
    private String id;
    private String actividadId;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha_sesion;
    private LocalTime hora_inicio;
    private LocalTime hora_fin;
    private String ponente;

    private Modalidad modalidad;
    private String lugar_sesion;
    private String link_virtual;
    private String orden;

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

    public Modalidad getModalidad() {
        return modalidad;
    }
    public void setModalidad(Modalidad modalidad) {
        if (modalidad == null) {
            throw new IllegalArgumentException("La modalidad no puede ser nula");
        }
        this.modalidad = modalidad;
    }


    public String getLugarSesion() {
        return lugar_sesion;
    }
    public void setLugarSesion(String lugar_sesion) {
        this.lugar_sesion = lugar_sesion;
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

    /* MÉTODOS DE FÁBRICA DEL DOMINIO */

    /**
     * Factory method para crear una nueva sesión
     */
    public static SesionDomainEntity create(
        String actividadId,
        String titulo,
        String descripcion,
        LocalDateTime fecha_sesion,
        LocalTime hora_inicio,
        LocalTime hora_fin,
        String ponente,
        Modalidad modalidad,
        String lugar_sesion,
        String link_virtual,
        String orden
    ) {
        validateCreationParams(actividadId, titulo, descripcion, fecha_sesion, hora_inicio, hora_fin, ponente, modalidad, lugar_sesion, link_virtual, orden);
        
        LocalDateTime now = LocalDateTime.now();

        SesionDomainEntity sesion = new SesionDomainEntity();
        sesion.setActividadId(actividadId);
        sesion.setTitulo(titulo);
        sesion.setDescripcion(descripcion);
        sesion.setFechaSesion(fecha_sesion);
        sesion.setHoraInicio(hora_inicio);
        sesion.setHoraFin(hora_fin);
        sesion.setPonente(ponente);
        sesion.setModalidad(modalidad);
        sesion.setLugarSesion(lugar_sesion);
        sesion.setLinkVirtual(link_virtual);
        sesion.setOrden(orden);
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
        String titulo,
        String descripcion,
        LocalDateTime fecha_sesion,
        LocalTime hora_inicio,
        LocalTime hora_fin,
        String ponente,
        Modalidad modalidad,
        String lugar_sesion,
        String link_virtual,
        String orden,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        SesionDomainEntity sesion = new SesionDomainEntity();
        sesion.setId(id);
        sesion.setActividadId(actividadId);
        sesion.setTitulo(titulo);
        sesion.setDescripcion(descripcion);
        sesion.setFechaSesion(fecha_sesion);
        sesion.setHoraInicio(hora_inicio);
        sesion.setHoraFin(hora_fin);
        sesion.setPonente(ponente);
        sesion.setModalidad(modalidad);
        sesion.setLugarSesion(lugar_sesion);
        sesion.setLinkVirtual(link_virtual);
        sesion.setOrden(orden);
        sesion.setCreatedAt(createdAt);
        sesion.setUpdatedAt(updatedAt);
        
        return sesion;
    }
    /* MÉTODOS DE NEGOCIO */

    /**
     * Actualiza la información de la sesión
     * Si actividadId es null, mantiene el actividadId existente
     */
    public void updateInfo( String titulo, String descripcion, LocalDateTime fecha_sesion, LocalTime hora_inicio, LocalTime hora_fin, String ponente, Modalidad modalidad, String lugar_sesion, String link_virtual, String orden) {
        // Si no se proporciona actividadId, usar el existente

        validateUpdateParams(titulo, fecha_sesion, hora_inicio, hora_fin, ponente);
        
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
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Reprograma la sesión a una nueva fecha
     */
    public void reprogramar(LocalDateTime nuevaFecha, LocalDate fechaActividadInicio, LocalDate fechaActividadFin) {
       if (nuevaFecha == null) {
        throw new IllegalArgumentException("La nueva fecha no puede ser nula");
    }
    if (nuevaFecha.isBefore(LocalDateTime.now())) {
        throw new IllegalArgumentException("No se puede reprogramar a una fecha pasada");
    }
    validateFechaWithinActivity(nuevaFecha, fechaActividadInicio, fechaActividadFin);
    
    this.fecha_sesion = nuevaFecha;
    this.updatedAt = LocalDateTime.now();

    }

    /**
     * Verifica si la sesión está en progreso
     */
    public boolean estaEnProgreso() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inicio = LocalDateTime.of(fecha_sesion.toLocalDate(), hora_inicio);
        LocalDateTime fin = LocalDateTime.of(fecha_sesion.toLocalDate(), hora_fin);
        return now.isAfter(inicio) && now.isBefore(fin);
    }

    /**
     * Verifica si la sesión ya finalizó
     */
    public boolean yaFinalizo() {
        LocalDateTime fin = LocalDateTime.of(fecha_sesion.toLocalDate(), hora_fin);
        return LocalDateTime.now().isAfter(fin);
    }
    /**
     * Verifica si la sesión es próxima (dentro de las próximas 24 horas)
     */
    public boolean esProxima() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = ahora.plusHours(24);
        return fecha_sesion.isAfter(ahora) && fecha_sesion.isBefore(limite);
    }

    private static void validateFechaWithinActivity(LocalDateTime fechaSesion, LocalDate fechaActividadInicio, LocalDate fechaActividadFin) {
    if (fechaActividadInicio == null || fechaActividadFin == null) {
        throw new IllegalArgumentException("Las fechas de la actividad no pueden ser nulas");
    }
    
    LocalDate fechaSesionDate = fechaSesion.toLocalDate();
    
    if (fechaSesionDate.isBefore(fechaActividadInicio) || fechaSesionDate.isAfter(fechaActividadFin)) {
        throw new IllegalArgumentException(
            "La fecha de la sesión (" + fechaSesionDate + ") debe estar entre " + 
            fechaActividadInicio + " y " + fechaActividadFin
        );
    }
}


    /* VALIDACIONES PRIVADAS */


    public boolean isValidWithinActivity(LocalDate fechaActividadInicio, LocalDate fechaActividadFin) {
    LocalDate fechaSesionDate = fecha_sesion.toLocalDate();
    // La fecha de sesión debe estar entre el inicio y fin de la actividad
    return !fechaSesionDate.isBefore(fechaActividadInicio) && 
           !fechaSesionDate.isAfter(fechaActividadFin);
        }

    private static void validateCreationParams(String actividadId, String titulo, String descripcion, LocalDateTime fecha_sesion, LocalTime hora_inicio, LocalTime hora_fin, String ponente, Modalidad modalidad, String lugar_sesion, String link_virtual, String orden) {
        if (actividadId == null) {
            throw new IllegalArgumentException("El ID de actividad no puede ser nulo");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (titulo.length() > 150) {
            throw new IllegalArgumentException("El título no puede exceder 150 caracteres");
        }
        if (descripcion != null && descripcion.length() > 500) {
            throw new IllegalArgumentException("La descripción no puede exceder 500 caracteres");
        }
        if (fecha_sesion == null) {
            throw new IllegalArgumentException("La fecha de sesión no puede ser nula");
        }
        if (hora_inicio == null) {
            throw new IllegalArgumentException("La hora de inicio no puede ser nula");
        }
        if (hora_fin == null) {
            throw new IllegalArgumentException("La hora de fin no puede ser nula");
        }
        if (ponente == null) {
            throw new IllegalArgumentException("El ID del ponente no puede ser nulo");
        }
    }
    private static void validateUpdateParams( String titulo, LocalDateTime fecha_sesion, LocalTime hora_inicio, LocalTime hora_fin, String ponente) {

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if (titulo.length() > 150) {
            throw new IllegalArgumentException("El título no puede exceder 150 caracteres");
        }
        if (fecha_sesion == null) {
            throw new IllegalArgumentException("La fecha de sesión no puede ser nula");
        }
        if (hora_inicio == null) {
            throw new IllegalArgumentException("La hora de inicio no puede ser nula");
        }
        if (hora_fin == null) {
            throw new IllegalArgumentException("La hora de fin no puede ser nula");
        }
        if (ponente == null) {
            throw new IllegalArgumentException("El ID del ponente no puede ser nulo");
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
                ", fecha_sesion=" + fecha_sesion +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}