package com.zentry.sigea.module_usuarios.core.entities;

import java.time.LocalDate;

public class InscripcionDomainEntity {
    private LocalDate fechaInscripcion;
    private String usuarioId;
    private String actividadId;
    private String estadoInscripcionId;

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

    public String getEstadoInscripcionId() {
        return estadoInscripcionId;
    }
    public void setEstadoInscripcionId(String estadoInscripcionId) {
        this.estadoInscripcionId = estadoInscripcionId;
    }

    public static InscripcionDomainEntity create(
        String usuarioId , 
        String actividadId , 
        String estadoInscripcionId
    ){
        LocalDate nowLocalDate = LocalDate.now();

        InscripcionDomainEntity inscripcionDomainEntity = new InscripcionDomainEntity();

        inscripcionDomainEntity.setUsuarioId(usuarioId);
        inscripcionDomainEntity.setActividadId(actividadId);
        inscripcionDomainEntity.setEstadoInscripcionId(estadoInscripcionId);
        inscripcionDomainEntity.setFechaInscripcion(nowLocalDate);

        return inscripcionDomainEntity;

    }

}
