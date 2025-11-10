package com.zentry.sigea.module_usuarios.infrastructure.database.mappers;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_usuarios.core.entities.InscripcionDomainEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.EstadoInscripcionEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.InscripcionEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

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
