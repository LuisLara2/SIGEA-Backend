package com.zentry.sigea.module_certificaciones.infrastructure.database.mappers;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_certificaciones.core.entities.ValidacionDomainEntity;
import com.zentry.sigea.module_certificaciones.infrastructure.database.entities.ValidacionEntity;

/**
 * Mapper para convertir entre entidades de dominio e infraestructura de Validacion
 */
@Component
public class ValidacionMapper {

    private final CertificadoMapper certificadoMapper;

    public ValidacionMapper(CertificadoMapper certificadoMapper) {
        this.certificadoMapper = certificadoMapper;
    }

    /**
     * Convierte de entidad de infraestructura a entidad de dominio
     */
    public ValidacionDomainEntity toDomain(ValidacionEntity validacion) {
        if (validacion == null) {
            return null;
        }

        ValidacionDomainEntity domainEntity = new ValidacionDomainEntity();
        
        // Convertir UUID a String para el certificado con verificación de null
        if (validacion.getCertificado() != null && validacion.getCertificado().getIdCertificado() != null) {
            domainEntity.setCertificado(validacion.getCertificado().getIdCertificado().toString());
        }
        
        // El tipo validador ya es String en ambas entidades
        domainEntity.setTipoValidador(validacion.getTipoValidador());
        domainEntity.setFechaValidacion(validacion.getFechaValidacion());
        domainEntity.setResultado(validacion.getResultado());
        domainEntity.setDetalle(validacion.getDetalle());

        return domainEntity;
    }

    /**
     * Convierte de entidad de dominio a entidad de infraestructura
     * NOTA: Este método requiere cargar las entidades relacionadas desde los repositorios
     * Debe ser usado con precaución y preferiblemente en un contexto de servicio
     */
    public ValidacionEntity toInfrastructure(ValidacionDomainEntity domainEntity) {
        if (domainEntity == null) {
            return null;
        }

        ValidacionEntity validacion = new ValidacionEntity();
        // NOTA: Aquí necesitarías cargar las entidades desde los repositorios usando los IDs
        // Por ahora dejamos comentado para evitar errores de compilación
        // validacion.setCertificado(certificadoRepository.findById(domainEntity.getCertificado()));
        // validacion.setTipoValidador(tipoValidadorRepository.findByCodigo(domainEntity.getTipoValidador()));
        validacion.setFechaValidacion(domainEntity.getFechaValidacion());
        validacion.setResultado(domainEntity.getResultado());
        validacion.setDetalle(domainEntity.getDetalle());

        return validacion;
    }

    /**
     * Actualiza una entidad de infraestructura con los datos de dominio
     */
    public void updateInfrastructure(ValidacionEntity validacion, ValidacionDomainEntity domainEntity) {
        if (validacion == null || domainEntity == null) {
            return;
        }

        // No actualizamos certificado y tipoValidador ya que son claves de relación
        validacion.setFechaValidacion(domainEntity.getFechaValidacion());
        validacion.setResultado(domainEntity.getResultado());
        validacion.setDetalle(domainEntity.getDetalle());
    }
}