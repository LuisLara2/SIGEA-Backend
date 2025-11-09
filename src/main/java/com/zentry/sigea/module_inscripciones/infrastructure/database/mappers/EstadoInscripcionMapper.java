package com.zentry.sigea.module_inscripciones.infrastructure.database.mappers;

import com.zentry.sigea.module_inscripciones.core.entities.EstadoInscripcionDomainEntity;
import com.zentry.sigea.module_inscripciones.infrastructure.database.entities.EstadoInscripcionEntity;

/**
 * Mapper para convertir entre EstadoInscripcion (dominio) y EstadoInscripcionEntity (JPA)
 */
public class EstadoInscripcionMapper {
    
    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    public static EstadoInscripcionEntity toEntity(EstadoInscripcionDomainEntity estadoInscripcionDomainEntity) {
        if (estadoInscripcionDomainEntity == null) {
            return null;
        }
        
        EstadoInscripcionEntity estadoInscripcionEntity = new EstadoInscripcionEntity();

        estadoInscripcionEntity.setCodigo(estadoInscripcionDomainEntity.getCodigo());
        estadoInscripcionEntity.setEtiqueta(estadoInscripcionDomainEntity.getEtiqueta());
        
        return estadoInscripcionEntity;
    }

    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    public static EstadoInscripcionDomainEntity toDomain(EstadoInscripcionEntity estadoInscripcionEntity) {
        if (estadoInscripcionEntity == null) {
            return null;
        }

        EstadoInscripcionDomainEntity estadoInscripcionDomainEntity = new EstadoInscripcionDomainEntity();

        estadoInscripcionDomainEntity.setCodigo(estadoInscripcionEntity.getCodigo());
        estadoInscripcionDomainEntity.setEtiqueta(estadoInscripcionEntity.getEtiqueta());
        
        return estadoInscripcionDomainEntity;
    }
}

