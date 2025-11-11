package com.zentry.sigea.module_usuarios.core.entities;

import java.time.LocalDateTime;

public class AsistenciaDomainEntity {
    private String sesionId;
    private String inscripcionId;
    private Boolean presente;
    private LocalDateTime registradoEn;

    public String getSesionId() {
        return sesionId;
    }
    public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }

    public String getInscripcionId() {
        return inscripcionId;
    }
    public void setInscripcionId(String inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public Boolean getPresente() {
        return presente;
    }
    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public LocalDateTime getRegistradoEn() {
        return registradoEn;
    }
    public void setRegistradoEn(LocalDateTime registradoEn) {
        this.registradoEn = registradoEn;
    }

    public static AsistenciaDomainEntity create(
        String sesionId,
        String inscripcionId,
        Boolean presente
    ){
        LocalDateTime nowLocalDateTime = LocalDateTime.now();

        AsistenciaDomainEntity asistenciaDomainEntity = new AsistenciaDomainEntity();

        asistenciaDomainEntity.setSesionId(sesionId);
        asistenciaDomainEntity.setInscripcionId(inscripcionId);
        asistenciaDomainEntity.setPresente(presente);
        asistenciaDomainEntity.setRegistradoEn(nowLocalDateTime);

        return asistenciaDomainEntity;
    }
}
