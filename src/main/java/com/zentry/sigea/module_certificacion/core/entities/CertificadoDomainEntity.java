package com.zentry.sigea.module_certificacion.core.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CertificadoDomainEntity {
    private String id;
    private String asistenciaId;
    private String codigoValidacion;
    private LocalDate fechaEmision;
    private EstadoCertificadoDomainEntity estadoCertificadoDomainEntity;
    private String urlPdf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAl;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getAsistenciaId() {
        return asistenciaId;
    }
    public void setAsistenciaId(String asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }
    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }
    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public EstadoCertificadoDomainEntity getEstadoCertificadoDomainEntity() {
        return estadoCertificadoDomainEntity;
    }
    public void setEstadoCertificadoDomainEntity(EstadoCertificadoDomainEntity estadoCertificadoDomainEntity) {
        this.estadoCertificadoDomainEntity = estadoCertificadoDomainEntity;
    }

    public String getUrlPdf() {
        return urlPdf;
    }
    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAl() {
        return updatedAl;
    }
    public void setUpdatedAl(LocalDateTime updatedAl) {
        this.updatedAl = updatedAl;
    }

    public static CertificadoDomainEntity create(
        String asistenciaId,
        String codigoValidacion,
        LocalDate fechaEmision,
        EstadoCertificadoDomainEntity estadoCertificadoDomainEntity,
        String urlPdf
    ){
        LocalDateTime nowLocalDateTime = LocalDateTime.now();

        CertificadoDomainEntity certificadoDomainEntity = new CertificadoDomainEntity();

        certificadoDomainEntity.setAsistenciaId(asistenciaId);
        certificadoDomainEntity.setCodigoValidacion(codigoValidacion);
        certificadoDomainEntity.setFechaEmision(fechaEmision);
        certificadoDomainEntity.setEstadoCertificadoDomainEntity(estadoCertificadoDomainEntity);
        certificadoDomainEntity.setUrlPdf(urlPdf);
        certificadoDomainEntity.setCreatedAt(nowLocalDateTime);
        certificadoDomainEntity.setUpdatedAl(nowLocalDateTime);

        return certificadoDomainEntity;
    }
}
