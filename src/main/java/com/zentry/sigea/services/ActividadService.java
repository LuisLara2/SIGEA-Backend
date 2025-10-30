package com.zentry.sigea.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zentry.sigea.core.entities.actividad.Actividad;
import com.zentry.sigea.core.repositories.ActividadRepository;
import com.zentry.sigea.core.services.usecases.crear_actividad.CrearActividadUseCase;
import com.zentry.sigea.core.value_objects.EstadoActividad;
import com.zentry.sigea.presentation.models.ActividadRequest;

/**
 * Servicio de Spring que coordina las operaciones de actividades
 * Actúa como fachada entre los controladores y los use cases del core
 */
@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final CrearActividadUseCase crearActividadUseCase;

    public ActividadService(ActividadRepository actividadRepository) {
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




        if (id == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo");
        }

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