package com.zentry.sigea.module_actividad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.infrastructure.repository.actividad_repository.ActividadRepositoryImpl;
import com.zentry.sigea.module_actividad.presentation.models.ActividadRequest;
import com.zentry.sigea.module_actividad.presentation.models.ActividadResponse;
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
     * Crea una nueva actividad
     */
    @Override
    public Actividad crearActividad(ActividadRequest request) {
        return crearActividadUseCase.execute(request);
    }

    /**
     * Obtiene una actividad por ID
     */
    public Optional<Actividad> obtenerPorId(Long id) {
        return actividadRepository.findById(id);
    }

    /**
     * Lista actividades con filtros opcionales
     */
    public List<Actividad> listarActividades(Optional<EstadoActividad> estado, Optional<Long> tipoId) {
        // Por ahora devolvemos todas, luego implementaremos filtros
        return actividadRepository.findAll();
    }

    /**
     * Actualiza una actividad existente
     */
    public Actividad actualizarActividad(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    /**
     * Elimina una actividad por ID
     */
    public void eliminarActividad(Long id) {
        actividadRepository.deleteById(id);
    }

    /**
     * Publica una actividad (cambia su estado)
     */
    public Actividad publicarActividad(Long id) {
        // TODO: Implementar lógica de cambio de estado
        var actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con ID: " + id));
        return actividadRepository.save(actividad);
    }

    @Override
    public ActividadResponse actualizarActividad(Long id, ActividadRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarActividad'");
    }

    @Override
    public ActividadResponse obtenerActividadPorId(Long id) {
        var actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada con ID: " + id));
        
        // Usar el factory method de ActividadResponse
        return ActividadResponse.fromEntity(actividad);
    }

    @Override
    public List<ActividadResponse> obtenerTodasLasActividades() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodasLasActividades'");
    }

    @Override
    public List<ActividadResponse> obtenerActividadesPorUsuario(Long usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerActividadesPorUsuario'");
    }

    @Override
    public List<ActividadResponse> obtenerActividadesPorTipo(Long tipoActividadId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerActividadesPorTipo'");
    }

}