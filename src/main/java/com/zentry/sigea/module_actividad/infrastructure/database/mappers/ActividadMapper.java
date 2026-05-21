package com.zentry.sigea.module_actividad.infrastructure.database.mappers;

import java.util.UUID;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

public class ActividadMapper {

    /**
     * Valida si un string es un UUID válido
     */
    private static boolean isValidUUID(String str) {
        if (str == null || str.isBlank()) {
            return false;
        }
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static ActividadEntity toEntity(
        ActividadDomainEntity actividadDomainEntity ,
        UsuarioEntity usuarioEntity
    ){
        ActividadEntity actividadEntity = new ActividadEntity();
        
        String actividadId = actividadDomainEntity.getActividadId();
        if (actividadId != null && !actividadId.isBlank()) {
            if (!isValidUUID(actividadId)) {
                throw new IllegalArgumentException("El actividadId no es un UUID válido: " + actividadId);
            }
            actividadEntity.setId(UUID.fromString(actividadId));
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
        actividadEntity.setOrganizador(usuarioEntity);
        actividadEntity.setTipoActividad(
            TipoActividadMapper.toEntity(
                actividadDomainEntity.getTipoActividadDomainEntity()
            )
        );
        actividadEntity.setLugar(actividadDomainEntity.getLugar());
        actividadEntity.setCoOrganizador(actividadDomainEntity.getCoOrganizador());
        actividadEntity.setSponsor(actividadDomainEntity.getSponsor());
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
        // Usamos el usuarioId como organizadorId
        actividadDomainEntity.setOrganizadorId(
            actividadEntity.getOrganizador().getId().toString()
        );
        actividadDomainEntity.setLugar(actividadEntity.getLugar());
        actividadDomainEntity.setCoOrganizador(actividadEntity.getCoOrganizador());
        actividadDomainEntity.setSponsor(actividadEntity.getSponsor());
        actividadDomainEntity.setBannerUrl(actividadEntity.getBannerUrl());
        actividadDomainEntity.setNumeroYape(actividadEntity.getNumeroYape());
        actividadDomainEntity.setCreatedAt(actividadEntity.getCreatedAt());
        actividadDomainEntity.setUpdatedAt(actividadEntity.getUpdatedAt());

        return actividadDomainEntity;
    }
}