package com.zentry.sigea.module_actividad.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.services.usecases.crear_actividad.CrearActividadUseCase;
import com.zentry.sigea.module_actividad.core.value_objects.EstadoActividad;
import com.zentry.sigea.module_actividad.infrastructure.repository.actividad_repository.ActividadRepositoryImpl;
import com.zentry.sigea.module_actividad.presentation.models.ActividadRequest;

/**
 * Servicio de Spring que coordina las operaciones de actividades
 */
@Service
public class ActividadService {

    private final ActividadRepositoryImpl actividadRepository;
    private final CrearActividadUseCase crearActividadUseCase;

    public ActividadService(ActividadRepositoryImpl actividadRepository) {
        this.actividadRepository = actividadRepository;
        this.crearActividadUseCase = new CrearActividadUseCase(actividadRepository);

    }

    /**
     * Crea una nueva actividad
     */
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
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        return actividadRepository.save(actividad);
    }
}