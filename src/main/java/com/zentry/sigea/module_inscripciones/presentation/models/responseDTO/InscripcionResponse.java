package com.zentry.sigea.module_inscripciones.presentation.models.responseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.zentry.sigea.module_inscripciones.core.entities.EstadoInscripcionDomainEntity;
import com.zentry.sigea.module_inscripciones.core.entities.InscripcionDomainEntity;

/**
 * DTO para enviar datos de inscripción al frontend
 */
public class InscripcionResponse {
    private String id;
    private LocalDate fechaInscripcion;
    private String usuarioId;
    private String actividadId;
    private EstadoInscripcionDomainEntity estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    
    // Campos adicionales para la vista
    private boolean confirmada;
    private boolean cancelada;
    private boolean pendiente;

    // Constructor vacío para Jackson
    public InscripcionResponse() {}

    public InscripcionResponse(
        String id,
        LocalDate fechaInscripcion,
        String usuarioId,
        String actividadId,
        EstadoInscripcionDomainEntity estado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        boolean confirmada,
        boolean cancelada,
        boolean pendiente
    ) {
        this.id = id;
        this.fechaInscripcion = fechaInscripcion;
        this.usuarioId = usuarioId;
        this.actividadId = actividadId;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.confirmada = confirmada;
        this.cancelada = cancelada;
        this.pendiente = pendiente;
    }

    /**
     * Factory method para crear un InscripcionResponse desde una entidad Inscripcion
     * Nota: Este método requiere que la entidad tenga un ID, pero la entidad de dominio no lo tiene
     * Se puede obtener del mapper o agregar el ID a la entidad de dominio si es necesario
     */
    public static InscripcionResponse fromEntity(InscripcionDomainEntity inscripcionDomainEntity) {
        return new InscripcionResponse(
            null, // El ID se puede agregar si es necesario
            inscripcionDomainEntity.getFechaInscripcion(),
            inscripcionDomainEntity.getUsuarioId(),
            inscripcionDomainEntity.getActividadId(),
            inscripcionDomainEntity.getEstadoInscripcionDomainEntity(),
            inscripcionDomainEntity.getCreatedAt(),
            inscripcionDomainEntity.getUpdatedAt(),
            inscripcionDomainEntity.estaConfirmada(),
            inscripcionDomainEntity.estaCancelada(),
            inscripcionDomainEntity.estaPendiente()
        );
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getActividadId() {
        return actividadId;
    }

    public void setActividadId(String actividadId) {
        this.actividadId = actividadId;
    }

    public EstadoInscripcionDomainEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcionDomainEntity estado) {
        this.estado = estado;
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

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }
}

