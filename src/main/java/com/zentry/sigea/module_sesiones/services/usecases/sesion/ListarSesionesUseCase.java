package com.zentry.sigea.module_sesiones.services.usecases.sesion;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Component;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;
import java.util.List;
import java.util.UUID;

/**
 * Caso de uso para listar todas las sesiones
 */
@Component
public class ListarSesionesUseCase {
    
    private final ISesionRepository sesionRepository;

    public ListarSesionesUseCase(ISesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public List<SesionDomainEntity> execute() {
        return sesionRepository.findAll();
    }
    
    public List<SesionDomainEntity> executeByActividad(String actividadId) {
        if (actividadId == null || actividadId.toString().isEmpty()) {
            throw new IllegalArgumentException("El ID de actividad debe ser un número positivo");
        }
        return sesionRepository.findByActividadId(actividadId);
    }

    public List<SesionDomainEntity> executeByActividadAndModalidad(String actividadId, Modalidad modalidad) {
        return sesionRepository.findByActividadIdAndModalidad(UUID.fromString(actividadId), modalidad);
    }

    public List<SesionDomainEntity> executeByModalidad(Modalidad modalidad) {
        return sesionRepository.findByModalidad(modalidad);
    }
}