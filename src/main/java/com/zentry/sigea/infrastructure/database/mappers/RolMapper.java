package com.zentry.sigea.infrastructure.database.mappers;

import com.zentry.sigea.core.entities.RolDomainEntity;
import com.zentry.sigea.infrastructure.database.entities.RolEntity;

public class RolMapper {
    public static RolEntity toEntity(RolDomainEntity rolDomainEntity){
        RolEntity rolEntity = new RolEntity();

        rolEntity.setNombreRol(rolDomainEntity.getNombreRol());
        rolEntity.setDescripcion(rolDomainEntity.getDescripcion());

        return rolEntity;
    }

    public static RolDomainEntity toDomain(RolEntity rolEntity){
        RolDomainEntity rolDomainEntity = new RolDomainEntity();

        rolDomainEntity.setNombreRol(rolEntity.getNombreRol());
        rolDomainEntity.setDescripcion(rolEntity.getDescripcion());

        return rolDomainEntity;
    }
}
