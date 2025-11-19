package com.zentry.sigea.module_certificacion.infrastructure.database.mappers;

import java.util.UUID;

import com.zentry.sigea.module_certificacion.core.entities.EstadoCertificadoDomainEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.EstadoCertificadoEntity;

public class EstadoCertificadoMapper {
    public static EstadoCertificadoEntity toEntity(EstadoCertificadoDomainEntity estadoCertificadoDomainEntity){
        EstadoCertificadoEntity estadoCertificadoEntity = new EstadoCertificadoEntity();

        estadoCertificadoEntity.setId(UUID.fromString(estadoCertificadoDomainEntity.getId()));
        estadoCertificadoEntity.setCodigo(estadoCertificadoDomainEntity.getCodigo());
        estadoCertificadoEntity.setEtiqueta(estadoCertificadoDomainEntity.getEtiqueta());

        return estadoCertificadoEntity;
    }

    public static EstadoCertificadoDomainEntity toDomain(EstadoCertificadoEntity estadoCertificadoEntity){
        EstadoCertificadoDomainEntity estadoCertificadoDomainEntity = new EstadoCertificadoDomainEntity();

        estadoCertificadoDomainEntity.setId(estadoCertificadoEntity.getId().toString());
        estadoCertificadoDomainEntity.setCodigo(estadoCertificadoEntity.getCodigo());
        estadoCertificadoDomainEntity.setEtiqueta(estadoCertificadoEntity.getEtiqueta());

        return estadoCertificadoDomainEntity;
    }
}
