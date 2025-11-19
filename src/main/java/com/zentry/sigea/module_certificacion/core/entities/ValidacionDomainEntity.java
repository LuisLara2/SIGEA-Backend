package com.zentry.sigea.module_certificacion.core.entities;

import java.time.LocalDate;

public class ValidacionDomainEntity {
    private String id;
    private String certificadoId;
    private TipoValidadorDomainEntity tipoValidadorDomainEntity;
    private LocalDate fechaValidacion;
    private String resultado;
    private String detalle;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCertificadoId() {
        return certificadoId;
    }
    public void setCertificadoId(String certificadoId) {
        this.certificadoId = certificadoId;
    }

    public TipoValidadorDomainEntity getTipoValidadorDomainEntity() {
        return tipoValidadorDomainEntity;
    }
    public void setTipoValidadorDomainEntity(TipoValidadorDomainEntity tipoValidadorDomainEntity) {
        this.tipoValidadorDomainEntity = tipoValidadorDomainEntity;
    }

    public LocalDate getFechaValidacion() {
        return fechaValidacion;
    }
    public void setFechaValidacion(LocalDate fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public static ValidacionDomainEntity create(
        String certificadoId,
        TipoValidadorDomainEntity tipoValidadorDomainEntity,
        String resultado,
        String detalle
    ){
        LocalDate nowLocalDate = LocalDate.now();

        ValidacionDomainEntity validacionDomainEntity = new ValidacionDomainEntity();

        validacionDomainEntity.setCertificadoId(certificadoId);
        validacionDomainEntity.setTipoValidadorDomainEntity(tipoValidadorDomainEntity);
        validacionDomainEntity.setFechaValidacion(nowLocalDate);
        validacionDomainEntity.setResultado(resultado != null ? resultado : "APROBADO");
        validacionDomainEntity.setDetalle(detalle);

        return validacionDomainEntity;
    }
}
