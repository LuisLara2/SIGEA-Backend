package com.zentry.sigea.infrastructure.database.mappers;

import com.zentry.sigea.core.entities.InscripcionDomainEntity;
import com.zentry.sigea.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.infrastructure.database.entities.EstadoInscripcionEntity;
import com.zentry.sigea.infrastructure.database.entities.InscripcionEntity;
import com.zentry.sigea.infrastructure.database.entities.UsuarioEntity;

public class InscripcionMapper {
    public static InscripcionEntity toEntity(
        InscripcionDomainEntity inscripcionDomainEntity , 
        UsuarioEntity usuarioEntity , 
        ActividadEntity actividadEntity , 
        EstadoInscripcionEntity estadoInscripcionEntity
    ){
        InscripcionEntity inscripcionEntity = new InscripcionEntity();

        inscripcionEntity.setFechaInscripcion(inscripcionDomainEntity.getFechaInscripcion());
        inscripcionEntity.setUsuario(usuarioEntity);
        inscripcionEntity.setActividad(actividadEntity);
        inscripcionEntity.setEstadoInscripcion(estadoInscripcionEntity);

        return inscripcionEntity;
    }

    public static InscripcionDomainEntity toDomain(InscripcionEntity inscripcionEntity){
        InscripcionDomainEntity inscripcionDomainEntity = new InscripcionDomainEntity();

        inscripcionDomainEntity.setFechaInscripcion(inscripcionEntity.getFechaInscripcion());
        inscripcionDomainEntity.setUsuarioId(
            inscripcionEntity.getUsuario().getId().toString()
        );
        inscripcionDomainEntity.setActividadId(
            inscripcionEntity.getActividad().getId().toString()
        );
        inscripcionDomainEntity.setEstadoInscripcionId(
            inscripcionEntity.getEstadoInscripcion().getId().toString()
        );

        return inscripcionDomainEntity;
    }
}
