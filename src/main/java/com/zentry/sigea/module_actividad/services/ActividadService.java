package com.zentry.sigea.module_actividad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.infrastructure.repository.actividad_repository.ActividadRepositoryImpl;
import com.zentry.sigea.module_actividad.presentation.models.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.ActividadResponse;
import com.zentry.sigea.module_actividad.presentation.models.CrearActividadRequest;
import com.zentry.sigea.module_actividad.services.interfaces.IActividad;
import com.zentry.sigea.module_actividad.services.usecases.actividad.CrearActividadUseCase;

/**
 * Servicio de aplicación que orquesta casos de uso de actividades
 * APPLICATION LAYER - Con anotaciones de Spring
 */
@Service
public class ActividadService implements IActividad {

    private final ActividadRepositoryImpl actividadRepository;
    private final CrearActividadUseCase crearActividadUseCase;

    public ActividadService(ActividadRepositoryImpl actividadRepository, 
                           CrearActividadUseCase crearActividadUseCase) {
        this.actividadRepository = actividadRepository;
        this.crearActividadUseCase = crearActividadUseCase;
    }

    /**
     * Crea una nueva actividad usando el request con IDs
     */
    @Override
    public Actividad crearActividad(CrearActividadRequest request) {
        return crearActividadUseCase.execute(request);
    }


    /**
     * Lista actividades con filtros opcionales
     */
    @Override
    @Transactional(readOnly = true)  // ← AGREGAR ESTO
    public List<ActividadResponse> listarActividades() {
        // Por ahora devolvemos todas, luego implementaremos filtros
        return actividadRepository.findAll().stream()
                .map(ActividadResponse::fromEntity)
                .toList();
    }


    @Override
    public ActividadResponse actualizarActividad(Long id, ActividadRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarActividad'");
    }

    @Override
    public ActividadResponse obtenerActividadPorId(Long id) {
        Optional<Actividad> actividad = actividadRepository.findById(id);
        if (actividad.isEmpty()) {
            throw new IllegalArgumentException("Actividad no encontrada con ID: " + id);
        }

        // Usar el factory method de ActividadResponse
        return ActividadResponse.fromEntity(actividad.get());
    }


    @Override
    public List<ActividadResponse> obtenerActividadesPorTipo(Long tipoActividadId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerActividadesPorTipo'");
    }

    @Override
    public void eliminarActividad(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarActividad'");
    }

}