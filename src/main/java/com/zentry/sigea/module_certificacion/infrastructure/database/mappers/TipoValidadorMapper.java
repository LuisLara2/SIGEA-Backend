package com.zentry.sigea.module_certificacion.infrastructure.database.mappers;

import java.util.UUID;

import com.zentry.sigea.module_certificacion.core.entities.TipoValidadorDomainEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.TipoValidadorEntity;

public class TipoValidadorMapper {
    public static TipoValidadorEntity toEntity(TipoValidadorDomainEntity tipoValidadorDomainEntity){
        TipoValidadorEntity tipoValidadorEntity = new TipoValidadorEntity();

        tipoValidadorEntity.setId(UUID.fromString(tipoValidadorDomainEntity.getId()));
        tipoValidadorEntity.setCodigo(tipoValidadorDomainEntity.getCodigo());
        tipoValidadorEntity.setEtiqueta(tipoValidadorDomainEntity.getEtiqueta());

        return tipoValidadorEntity;
    }

    public static TipoValidadorDomainEntity toDomain(TipoValidadorEntity tipoValidadorEntity){
        TipoValidadorDomainEntity tipoValidadorDomainEntity = new TipoValidadorDomainEntity();

        tipoValidadorDomainEntity.setId(tipoValidadorEntity.getId().toString());
        tipoValidadorDomainEntity.setCodigo(tipoValidadorEntity.getCodigo());
        tipoValidadorDomainEntity.setEtiqueta(tipoValidadorEntity.getEtiqueta());

        return tipoValidadorDomainEntity;
    }
}
