package com.zentry.sigea.module_certificacion.infrastructure.database.mappers;

import java.util.UUID;

import com.zentry.sigea.module_certificacion.core.entities.ValidacionDomainEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.CertificadoEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.TipoValidadorEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.ValidacionEntity;

public class ValidacionMapper {
    public static ValidacionEntity toEntity(
        ValidacionDomainEntity validacionDomainEntity , 
        CertificadoEntity certificadoEntity , 
        TipoValidadorEntity tipoValidadorEntity
    ){
        ValidacionEntity validacionEntity = new ValidacionEntity();

        validacionEntity.setId(UUID.fromString(validacionDomainEntity.getId()));
        validacionEntity.setCertificado(certificadoEntity);
        validacionEntity.setTipoValidador(tipoValidadorEntity);
        validacionEntity.setFechaValidacion(validacionDomainEntity.getFechaValidacion());
        validacionEntity.setResultado(validacionDomainEntity.getResultado());
        validacionEntity.setDetalle(validacionDomainEntity.getDetalle());

        return validacionEntity;
    }

    public static ValidacionDomainEntity toDomain(ValidacionEntity validacionEntity){
        ValidacionDomainEntity validacionDomainEntity = new ValidacionDomainEntity();

        validacionDomainEntity.setId(validacionEntity.getId().toString());
        validacionDomainEntity.setCertificadoId(
            validacionEntity.getCertificado().getId().toString()
        );
        validacionDomainEntity.setTipoValidadorDomainEntity(
            TipoValidadorMapper.toDomain(validacionEntity.getTipoValidador())
        );
        validacionDomainEntity.setFechaValidacion(validacionEntity.getFechaValidacion());
        validacionDomainEntity.setResultado(validacionEntity.getResultado());
        validacionDomainEntity.setDetalle(validacionEntity.getDetalle());

        return validacionDomainEntity;
    }
}
