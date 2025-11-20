package com.zentry.sigea.module_certificaciones.infrastructure.database.mappers;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_certificaciones.core.entities.CertificadoDomainEntity;
import com.zentry.sigea.module_certificaciones.infrastructure.database.entities.CertificadoEntity;

/**
 * Mapper para convertir entre entidades de dominio e infraestructura de Certificado
 */
@Component
public class CertificadoMapper {

    private final EstadoCertificadoMapper estadoMapper;

    public CertificadoMapper(EstadoCertificadoMapper estadoMapper) {
        this.estadoMapper = estadoMapper;
    }

    /**
     * Convierte de entidad de infraestructura a entidad de dominio
     */
    public CertificadoDomainEntity toDomain(CertificadoEntity certificado) {
        if (certificado == null) {
            return null;
        }

        CertificadoDomainEntity domainEntity = new CertificadoDomainEntity();
        
        // Convertir UUID a String con validación de null
        if (certificado.getIdCertificado() != null) {
            domainEntity.setIdCertificado(certificado.getIdCertificado().toString());
        }
        
        // Convertir UUID a String para asistenciaId
        if (certificado.getAsistenciaId() != null) {
            domainEntity.setAsistenciaId(certificado.getAsistenciaId().toString());
        }
        
        domainEntity.setCodigoValidacion(certificado.getCodigoValidacion());
        domainEntity.setFechaEmision(certificado.getFechaEmision());
        domainEntity.setEstado(estadoMapper.toDomain(certificado.getEstado()));
        domainEntity.setUrlPdf(certificado.getUrlPdf());
        domainEntity.setCreatedAt(certificado.getCreatedAt());
        domainEntity.setUpdatedAt(certificado.getUpdatedAt());

        return domainEntity;
    }

    /**
     * Convierte de entidad de dominio a entidad de infraestructura
     */
    public CertificadoEntity toInfrastructure(CertificadoDomainEntity domainEntity) {
        if (domainEntity == null) {
            return null;
        }

        CertificadoEntity certificado = new CertificadoEntity();
        
        // Convertir String a UUID para idCertificado
        if (domainEntity.getIdCertificado() != null) {
            certificado.setIdCertificado(java.util.UUID.fromString(domainEntity.getIdCertificado()));
        }
        
        // Convertir String a UUID para asistenciaId
        if (domainEntity.getAsistenciaId() != null) {
            certificado.setAsistenciaId(java.util.UUID.fromString(domainEntity.getAsistenciaId()));
        }
        
        certificado.setCodigoValidacion(domainEntity.getCodigoValidacion());
        certificado.setFechaEmision(domainEntity.getFechaEmision());
        certificado.setEstado(estadoMapper.toInfrastructure(domainEntity.getEstado()));
        certificado.setUrlPdf(domainEntity.getUrlPdf());
        certificado.setCreatedAt(domainEntity.getCreatedAt());
        certificado.setUpdatedAt(domainEntity.getUpdatedAt());

        return certificado;
    }

    /**
     * Actualiza una entidad de infraestructura con los datos de dominio
     */
    public void updateInfrastructure(CertificadoEntity certificado, CertificadoDomainEntity domainEntity) {
        if (certificado == null || domainEntity == null) {
            return;
        }

        // Convertir String a UUID para asistenciaId
        if (domainEntity.getAsistenciaId() != null) {
            certificado.setAsistenciaId(java.util.UUID.fromString(domainEntity.getAsistenciaId()));
        }
        
        certificado.setCodigoValidacion(domainEntity.getCodigoValidacion());
        certificado.setFechaEmision(domainEntity.getFechaEmision());
        certificado.setEstado(estadoMapper.toInfrastructure(domainEntity.getEstado()));
        certificado.setUrlPdf(domainEntity.getUrlPdf());
        certificado.setUpdatedAt(domainEntity.getUpdatedAt());
    }
}