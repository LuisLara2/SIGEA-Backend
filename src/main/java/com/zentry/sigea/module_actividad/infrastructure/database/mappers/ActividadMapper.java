package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioRolEntity;

public class ActividadMapper {

    public static ActividadEntity toEntity(
        ActividadDomainEntity actividadDomainEntity ,
        UsuarioRolEntity usuarioRolEntity
    ){
        ActividadEntity actividadEntity = new ActividadEntity();
        
        if (actividadDomainEntity.getActividadId() != null) {
            actividadEntity.setId(java.util.UUID.fromString(actividadDomainEntity.getActividadId()));
        }
        actividadEntity.setTitulo(actividadDomainEntity.getTitulo());
        actividadEntity.setDescripcion(actividadDomainEntity.getDescripcion());
        actividadEntity.setFechaInicio(actividadDomainEntity.getFechaInicio());
        actividadEntity.setFechaFin(actividadDomainEntity.getFechaFin());
        actividadEntity.setHoraInicio(actividadDomainEntity.getHoraInicio());
        actividadEntity.setHoraFin(actividadDomainEntity.getHoraFin());
        actividadEntity.setEstadoActividad(
            EstadoActividadMapper.toEntity(
                actividadDomainEntity.getEstadoActividadDomainEntity()
            )
        );
        actividadEntity.setOrganizador(usuarioRolEntity);
        actividadEntity.setTipoActividad(
            TipoActividadMapper.toEntity(
                actividadDomainEntity.getTipoActividadDomainEntity()
            )
        );
        actividadEntity.setLugar(actividadDomainEntity.getLugar());
        actividadEntity.setBannerUrl(actividadDomainEntity.getBannerUrl());
        actividadEntity.setNumeroYape(actividadDomainEntity.getNumeroYape());
        actividadEntity.setCreatedAt(actividadDomainEntity.getCreatedAt());
        actividadEntity.setUpdatedAt(actividadDomainEntity.getUpdatedAt());

        return actividadEntity;
    }

    public static ActividadDomainEntity toDomain(ActividadEntity actividadEntity){
        ActividadDomainEntity actividadDomainEntity = new ActividadDomainEntity();
        
        actividadDomainEntity.setActividadId(actividadEntity.getId().toString());
        actividadDomainEntity.setTitulo(actividadEntity.getTitulo());
        actividadDomainEntity.setDescripcion(actividadEntity.getDescripcion());
        actividadDomainEntity.setFechaInicio(actividadEntity.getFechaInicio());
        actividadDomainEntity.setFechaFin(actividadEntity.getFechaFin());
        actividadDomainEntity.setHoraInicio(actividadEntity.getHoraInicio());
        actividadDomainEntity.setHoraFin(actividadEntity.getHoraFin());
        actividadDomainEntity.setEstadoActividadDomainEntity(
            EstadoActividadMapper.toDomain(
                actividadEntity.getEstadoActividad()
            )
        );
        actividadDomainEntity.setTipoActividadDomainEntity(
            TipoActividadMapper.toDomain(
                actividadEntity.getTipoActividad()
            )
        );
        // Usamos el usuarioRolId como organizadorId
        actividadDomainEntity.setOrganizadorId(
            actividadEntity.getOrganizador().getUsuarioRolId().toString()
        );
        actividadDomainEntity.setLugar(actividadEntity.getLugar());
        actividadDomainEntity.setBannerUrl(actividadEntity.getBannerUrl());
        actividadDomainEntity.setNumeroYape(actividadEntity.getNumeroYape());
        actividadDomainEntity.setCreatedAt(actividadEntity.getCreatedAt());
        actividadDomainEntity.setUpdatedAt(actividadEntity.getUpdatedAt());

        return actividadDomainEntity;
    }
}