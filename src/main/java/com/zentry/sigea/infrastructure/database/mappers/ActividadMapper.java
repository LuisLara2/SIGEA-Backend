package com.zentry.sigea.infrastructure.database.mappers;

import org.springframework.stereotype.Component;

import com.zentry.sigea.core.entities.ActividadDomainEntity;
import com.zentry.sigea.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.infrastructure.database.entities.EstadoActividadEntity;
import com.zentry.sigea.infrastructure.database.entities.TipoActividadEntity;
import com.zentry.sigea.infrastructure.database.entities.UsuarioEntity;

public class ActividadMapper {

    public static ActividadEntity toEntity(
        ActividadDomainEntity actividadDomainEntity ,
        EstadoActividadEntity estadoActividadEntity , 
        UsuarioEntity usuarioEntity , 
        TipoActividadEntity tipoActividadEntity
    ){
        ActividadEntity actividadEntity = new ActividadEntity();

        actividadEntity.setTitulo(actividadDomainEntity.getTitulo());
        actividadEntity.setDescripcion(actividadDomainEntity.getDescripcion());
        actividadEntity.setFechaInicio(actividadDomainEntity.getFechaInicio());
        actividadEntity.setFechaFin(actividadDomainEntity.getFechaFin());
        actividadEntity.setEstadoActividad(estadoActividadEntity);
        actividadEntity.setOrganizador(usuarioEntity);
        actividadEntity.setTipoActividad(tipoActividadEntity);

        return actividadEntity;
    }

    public static ActividadDomainEntity toDomain(ActividadEntity actividadEntity){
        ActividadDomainEntity actividadDomainEntity = new ActividadDomainEntity();

        actividadDomainEntity.setTitulo(actividadEntity.getTitulo());
        actividadDomainEntity.setDescripcion(actividadEntity.getDescripcion());
        actividadDomainEntity.setFechaInicio(actividadEntity.getFechaInicio());
        actividadDomainEntity.setFechaFin(actividadEntity.getFechaFin());
        actividadDomainEntity.setEstadoActividadId(
            actividadEntity.getEstadoActividad().getId().toString()
        );
        actividadDomainEntity.setTipoActividadId(
            actividadEntity.getTipoActividad().getId().toString()
        );
        actividadDomainEntity.setOrganizadorId(
            actividadEntity.getOrganizador().getId().toString()
        );
        actividadDomainEntity.setLugar(actividadEntity.getLugar());

        return actividadDomainEntity;
    }
}
