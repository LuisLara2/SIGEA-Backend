package com.zentry.sigea.module_sesiones.services.usecases.sesion;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import org.springframework.stereotype.Component;

import java.util.List;

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
}