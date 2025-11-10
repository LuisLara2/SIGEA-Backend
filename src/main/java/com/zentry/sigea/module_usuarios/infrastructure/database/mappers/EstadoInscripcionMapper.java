package com.zentry.sigea.module_usuarios.infrastructure.database.mappers;

import com.zentry.sigea.module_usuarios.core.entities.EstadoInscripcionDomainEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.EstadoInscripcionEntity;

public class EstadoInscripcionMapper {
    public static EstadoInscripcionEntity toEntity(EstadoInscripcionDomainEntity estadoInscripcionDomainEntity){
        EstadoInscripcionEntity estadoInscripcionEntity = new EstadoInscripcionEntity();

        estadoInscripcionEntity.setCodigo(estadoInscripcionDomainEntity.getCodigo());
        estadoInscripcionEntity.setEtiqueta(estadoInscripcionDomainEntity.getEtiqueta());

        return estadoInscripcionEntity;
    }

    public static EstadoInscripcionDomainEntity toDomain(EstadoInscripcionEntity estadoInscripcionEntity){
        EstadoInscripcionDomainEntity estadoInscripcionDomainEntity = new EstadoInscripcionDomainEntity();

        estadoInscripcionDomainEntity.setCodigo(estadoInscripcionEntity.getCodigo());
        estadoInscripcionDomainEntity.setEtiqueta(estadoInscripcionEntity.getEtiqueta());

        return estadoInscripcionDomainEntity;
    }
}
