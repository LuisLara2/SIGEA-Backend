package com.zentry.sigea.module_certificacion.infrastructure.database.mappers;

import java.util.UUID;

import com.zentry.sigea.module_certificacion.core.entities.CertificadoDomainEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.CertificadoEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.EstadoCertificadoEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.AsistenciaEntity;

public class CertificadoMapper {
    public static CertificadoEntity toEntity(
        CertificadoDomainEntity certificadoDomainEntity ,
        AsistenciaEntity asistenciaEntity , 
        EstadoCertificadoEntity estadoCertificadoEntity
    ){
        CertificadoEntity certificadoEntity = new CertificadoEntity();

        certificadoEntity.setId(UUID.fromString(certificadoDomainEntity.getId()));
        certificadoEntity.setAsistencia(asistenciaEntity);
        certificadoEntity.setCodigoValidacion(certificadoDomainEntity.getCodigoValidacion());
        certificadoEntity.setFechaEmision(certificadoDomainEntity.getFechaEmision());
        certificadoEntity.setEstadoCertificado(estadoCertificadoEntity);
        certificadoEntity.setUrlPdf(certificadoDomainEntity.getUrlPdf());
        certificadoEntity.setCreatedAt(certificadoDomainEntity.getCreatedAt());
        certificadoEntity.setUpdatedAt(certificadoDomainEntity.getUpdatedAl());

        return certificadoEntity;
    }

    public static CertificadoDomainEntity toDomain(CertificadoEntity certificadoEntity){
        CertificadoDomainEntity certificadoDomainEntity = new CertificadoDomainEntity();

        certificadoDomainEntity.setId(certificadoEntity.getId().toString());
        certificadoDomainEntity.setAsistenciaId(certificadoEntity.getAsistencia().getId().toString());
        certificadoDomainEntity.setCodigoValidacion(certificadoEntity.getCodigoValidacion());
        certificadoDomainEntity.setFechaEmision(certificadoEntity.getFechaEmision());
        certificadoDomainEntity.setEstadoCertificadoDomainEntity(
            EstadoCertificadoMapper.toDomain(certificadoEntity.getEstadoCertificado())
        );
        certificadoDomainEntity.setUrlPdf(certificadoEntity.getUrlPdf());
        certificadoDomainEntity.setCreatedAt(certificadoEntity.getCreatedAt());
        certificadoDomainEntity.setUpdatedAl(certificadoEntity.getUpdatedAt());

        return certificadoDomainEntity;
    }
}
