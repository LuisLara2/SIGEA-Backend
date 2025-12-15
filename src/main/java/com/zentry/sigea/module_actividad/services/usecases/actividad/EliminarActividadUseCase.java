package com.zentry.sigea.module_actividad.services.usecases.actividad;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_actividad.core.repositories.IActividadRespository;
import com.zentry.sigea.module_inscripciones.infrastructure.repository.InscripcionJPARepository;
import com.zentry.sigea.module_sesiones.infrastructure.repositories.SesionJPARepository;

@Component
public class EliminarActividadUseCase {

    private final IActividadRespository actividadRepository;
    private final InscripcionJPARepository inscripcionJPARepository;
    private final SesionJPARepository sesionJPARepository;

    public EliminarActividadUseCase(
        IActividadRespository actividadRepository,
        InscripcionJPARepository inscripcionJPARepository,
        SesionJPARepository sesionJPARepository
    ) {
        this.actividadRepository = actividadRepository;
        this.inscripcionJPARepository = inscripcionJPARepository;
        this.sesionJPARepository = sesionJPARepository;
    }

    @Transactional
    public void execute(String id) {
        if (!actividadRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe actividad con ID: " + id);
        }
        
        UUID actividadUUID = UUID.fromString(id);
        
        // 1. Eliminar todas las inscripciones asociadas
        inscripcionJPARepository.deleteByActividadId(actividadUUID);
        
        // 2. Eliminar todas las sesiones asociadas
        sesionJPARepository.deleteByActividadId(actividadUUID);
        
        // 3. Finalmente eliminar la actividad
        actividadRepository.deleteById(id);
    }
}
