package com.zentry.sigea.module_usuarios.infrastructure.database.mappers;

import com.zentry.sigea.module_usuarios.core.entities.CodigoVerificacionDomainEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.CodigoVerificacionEntity;

public class CodigoVerificacionMapper {
    public static CodigoVerificacionEntity toEntity(CodigoVerificacionDomainEntity codigoVerificacionDomainEntity){
        CodigoVerificacionEntity codigoVerificacionEntity = new CodigoVerificacionEntity();

        codigoVerificacionEntity.setCodigo(codigoVerificacionDomainEntity.getCodigo());
        codigoVerificacionEntity.setCorreo(codigoVerificacionDomainEntity.getCorreo());
        codigoVerificacionEntity.setExpiresAt(codigoVerificacionDomainEntity.getExpiresAt());

        return codigoVerificacionEntity;
    }

    public static CodigoVerificacionDomainEntity toDomain(CodigoVerificacionEntity codigoVerificacionEntity){
        CodigoVerificacionDomainEntity codigoVerificacionDomainEntity = new CodigoVerificacionDomainEntity();

        codigoVerificacionDomainEntity.setCodigo(codigoVerificacionDomainEntity.getCodigo());
        codigoVerificacionDomainEntity.setCorreo(codigoVerificacionDomainEntity.getCorreo());
        codigoVerificacionDomainEntity.setExpiresAt(codigoVerificacionDomainEntity.getExpiresAt());

        return codigoVerificacionDomainEntity;
    }
}
