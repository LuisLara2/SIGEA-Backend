package com.zentry.sigea.module_actividad.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.infrastructure.repository.estadoactividad_repository.EstadoActividadImpl;
import com.zentry.sigea.module_actividad.presentation.models.EstadoActividadRequest;
import com.zentry.sigea.module_actividad.services.interfaces.IEstadoActividad;
import com.zentry.sigea.module_actividad.services.usecases.estado_actividad.CrearEstadoActividadUseCase;

@Service
public class EstadoActividadService implements IEstadoActividad {

    private final EstadoActividadImpl estadoActividadRepository;
    private final CrearEstadoActividadUseCase crearEstadoActividadUseCase;
    
    // Constructor para inyección de dependencias
    public EstadoActividadService(EstadoActividadImpl estadoActividadRepository, CrearEstadoActividadUseCase crearEstadoActividadUseCase) {
        this.estadoActividadRepository = estadoActividadRepository;
        this.crearEstadoActividadUseCase = crearEstadoActividadUseCase;
    }
    @Override
    public EstadoActividad crearEstadoActividad(EstadoActividadRequest estadoActividad) {
        // Lógica para crear un nuevo estado de actividad
        return crearEstadoActividadUseCase.execute(estadoActividad);
    }

    @Override
    public EstadoActividad actualizarEstadoActividad(EstadoActividadRequest estadoActividad) {
        // Lógica para actualizar un estado de actividad existente

        return null;
    }

    @Override
    public void eliminarEstadoActividad(Long id) {
        // Lógica para eliminar un estado de actividad por su ID
        estadoActividadRepository.deleteById(id);
    }

    @Override
    public EstadoActividad obtenerEstadoActividadPorId(Long id) {
        // Lógica para obtener un estado de actividad por su ID
        return estadoActividadRepository.findById(id);
    }

    @Override
    public List<EstadoActividad> obtenerTodosLosEstadosActividad() {
        // Lógica para obtener todos los estados de actividad
        return estadoActividadRepository.findAll();
    }
    @Override
    public List<EstadoActividad> listarEstadosActividad() {
        return estadoActividadRepository.findAll();
    }
}