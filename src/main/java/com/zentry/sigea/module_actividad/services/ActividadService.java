package com.zentry.sigea.module_actividad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IActividadRespository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.CrearActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.responseDTO.ActividadResponse;
import com.zentry.sigea.module_actividad.services.interfaces.IActividad;
import com.zentry.sigea.module_actividad.services.usecases.actividad.CrearActividadUseCase;

/**
 * Servicio de aplicación que orquesta casos de uso de actividades
 * APPLICATION LAYER - Con anotaciones de Spring
 */
@Service
public class ActividadService implements IActividad {

    private final IActividadRespository actividadRespository;
    private final CrearActividadUseCase crearActividadUseCase;

    public ActividadService(
        IActividadRespository actividadRepository, 
        CrearActividadUseCase crearActividadUseCase
    ) {
        this.actividadRespository = actividadRepository;
        this.crearActividadUseCase = crearActividadUseCase;
    }

    /**
     * Crea una nueva actividad usando el request con IDs
     */
    @Override
    public String crearActividad(CrearActividadRequest request) {
        return crearActividadUseCase.execute(request);
    }


    /**
     * Lista actividades con filtros opcionales
     */
    @Override
    @Transactional(readOnly = true)  // ← AGREGAR ESTO
    public List<ActividadResponse> listarActividades() {
        // Por ahora devolvemos todas, luego implementaremos filtros
        return actividadRespository.findAll().stream()
                .map(ActividadResponse::fromEntity)
                .toList();
    }


    @Override
    public ActividadResponse actualizarActividad(String id, ActividadRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarActividad'");
    }

    @Override
    public ActividadResponse obtenerActividadPorId(String id) {
        Optional<ActividadDomainEntity> actividad = actividadRespository.findById(id);
        if (actividad.isEmpty()) {
            throw new IllegalArgumentException("Actividad no encontrada con ID: " + id);
        }

        // Usar el factory method de ActividadResponse
        return ActividadResponse.fromEntity(actividad.get());
    }


    @Override
    public List<ActividadResponse> obtenerActividadesPorTipo(String tipoActividadId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerActividadesPorTipo'");
    }

    @Override
    public void eliminarActividad(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarActividad'");
    }

}