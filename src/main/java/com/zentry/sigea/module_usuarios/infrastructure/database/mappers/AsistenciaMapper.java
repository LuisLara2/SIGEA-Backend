package com.zentry.sigea.module_usuarios.infrastructure.database.mappers;

import com.zentry.sigea.module_inscripciones.infrastructure.database.entities.InscripcionEntity;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity;
import com.zentry.sigea.module_usuarios.core.entities.AsistenciaDomainEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.AsistenciaEntity;

public class AsistenciaMapper {
    public static AsistenciaEntity toEntity(
        AsistenciaDomainEntity asistenciaDomainEntity , 
        SesionEntity sesionEntity , 
        InscripcionEntity inscripcionEntity
    ){
        AsistenciaEntity asistenciaEntity = new AsistenciaEntity();

        asistenciaEntity.setSesion(sesionEntity);
        asistenciaEntity.setInscripcion(inscripcionEntity);
        asistenciaEntity.setPresente(asistenciaDomainEntity.getPresente());
        asistenciaEntity.setRegistradoEn(asistenciaDomainEntity.getRegistradoEn());

        return asistenciaEntity;
    }

    public static AsistenciaDomainEntity toDomain(AsistenciaEntity asistenciaEntity){
        AsistenciaDomainEntity asistenciaDomainEntity = new AsistenciaDomainEntity();

        asistenciaDomainEntity.setSesionId(
            asistenciaEntity.getSesion().getId().toString()
        );
        asistenciaDomainEntity.setInscripcionId(
            asistenciaEntity.getInscripcion().getId().toString()
        );
        asistenciaDomainEntity.setPresente(asistenciaEntity.getPresente());
        asistenciaDomainEntity.setRegistradoEn(asistenciaEntity.getRegistradoEn());

        return asistenciaDomainEntity;
    }
}
