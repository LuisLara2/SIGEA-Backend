package com.zentry.sigea.module_certificaciones.services.usecases.certificado;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_certificaciones.core.entities.CertificadoDomainEntity;
import com.zentry.sigea.module_certificaciones.core.entities.EstadoCertificadoDomainEntity;
import com.zentry.sigea.module_certificaciones.core.repositories.ICertificadoRepository;
import com.zentry.sigea.module_certificaciones.core.repositories.IEstadoCertificadoRepository;
import com.zentry.sigea.module_certificaciones.presentation.models.requestDTO.CrearCertificadoRequest;

/**
 * Caso de uso para crear un nuevo certificado
 */
@Component
public class CrearCertificadoUseCase {

    private final ICertificadoRepository certificadoRepository;
    private final IEstadoCertificadoRepository estadoCertificadoRepository;

    public CrearCertificadoUseCase(
        ICertificadoRepository certificadoRepository,
        IEstadoCertificadoRepository estadoCertificadoRepository
    ) {
        this.certificadoRepository = certificadoRepository;
        this.estadoCertificadoRepository = estadoCertificadoRepository;
    }

    /**
     * Ejecuta la creación de certificado
     */
    public CertificadoDomainEntity execute(CrearCertificadoRequest request) {
        // Validar que no exista ya un certificado para esta inscripción
        String asistenciaId = request.getAsistenciaId().toString();
        if (certificadoRepository.existsByAsistenciaId(asistenciaId)) {
            throw new IllegalArgumentException(
                "Ya existe un certificado para esta asistencia: " + asistenciaId
            );
        }
        
        // Obtener estado EMITIDO por defecto
        EstadoCertificadoDomainEntity estadoEmitido = estadoCertificadoRepository
            .findByCodigo("EMITIDO")
            .orElseThrow(() -> new IllegalStateException("Estado EMITIDO no encontrado en el sistema"));
        
        // Generar código de validación único
        String codigoValidacion = generarCodigoValidacion();
        
        // Asegurar que el código sea único
        while (certificadoRepository.existsByCodigoValidacion(codigoValidacion)) {
            codigoValidacion = generarCodigoValidacion();
        }
        
        // Validaciones de negocio específicas del caso de uso
        validateBusinessRules(request);
        
        // Crear la entidad usando el factory method del dominio
        CertificadoDomainEntity nuevoCertificado = CertificadoDomainEntity.create(
            asistenciaId,
            codigoValidacion,
            estadoEmitido
        );
        
        // Guardar usando el repositorio
        return certificadoRepository.save(nuevoCertificado);
    }

    /**
     * Genera un código de validación único
     */
    private String generarCodigoValidacion() {
        return "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    /**
     * Validaciones de reglas de negocio específicas
     */
    private void validateBusinessRules(CrearCertificadoRequest request) {
        if (request.getAsistenciaId() == null) {
            throw new IllegalArgumentException("La asistencia ID es obligatoria");
        }
        
        // TODO: Validar que la asistencia exista y esté completada
        // Esto requeriría una consulta al módulo de inscripciones
        // Por ahora solo validamos el formato básico
    }
}