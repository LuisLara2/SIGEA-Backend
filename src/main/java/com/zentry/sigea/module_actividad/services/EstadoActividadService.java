package com.zentry.sigea.module_actividad.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;
import com.zentry.sigea.module_actividad.core.repositories.EstadoActividadRepository;
import com.zentry.sigea.module_actividad.services.interfaces.IEstadoActividad;

@Service
public class EstadoActividadService implements IEstadoActividad {

    private final EstadoActividadRepository estadoActividadRepository;
    
    // Constructor para inyección de dependencias
    public EstadoActividadService(EstadoActividadRepository estadoActividadRepository) {
        this.estadoActividadRepository = estadoActividadRepository;
    }
    @Override
    public EstadoActividad crearEstadoActividad(EstadoActividad estadoActividad) {
        // Lógica para crear un nuevo estado de actividad
        return estadoActividadRepository.save(estadoActividad);
    }

    @Override
    public EstadoActividad actualizarEstadoActividad(EstadoActividad estadoActividad) {
        // Lógica para actualizar un estado de actividad existente
        return estadoActividadRepository.save(estadoActividad);
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
}