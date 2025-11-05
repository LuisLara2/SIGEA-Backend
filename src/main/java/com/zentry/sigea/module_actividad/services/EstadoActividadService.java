package com.zentry.sigea.module_actividad.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.EstadoActividadRequest;
import com.zentry.sigea.module_actividad.services.interfaces.IEstadoActividad;
import com.zentry.sigea.module_actividad.services.usecases.estado_actividad.CrearEstadoActividadUseCase;

@Service
public class EstadoActividadService implements IEstadoActividad {

    private final IEstadoActividadRepository estadoActividadRepository;
    private final CrearEstadoActividadUseCase crearEstadoActividadUseCase;
    
    // Constructor para inyección de dependencias
    public EstadoActividadService(
        IEstadoActividadRepository estadoActividadRepository, 
        CrearEstadoActividadUseCase crearEstadoActividadUseCase
    ) {
        this.estadoActividadRepository = estadoActividadRepository;
        this.crearEstadoActividadUseCase = crearEstadoActividadUseCase;
    }

    @Override
    public String crearEstadoActividad(EstadoActividadRequest estadoActividad) {
        // Lógica para crear un nuevo estado de actividad
        return crearEstadoActividadUseCase.execute(estadoActividad);
    }

    @Override
    public EstadoActividadDomainEntity actualizarEstadoActividad(EstadoActividadRequest estadoActividad) {
        // Lógica para actualizar un estado de actividad existente

        return null;
    }

    @Override
    public void eliminarEstadoActividad(String id) {
        // Lógica para eliminar un estado de actividad por su ID
        estadoActividadRepository.deleteById(id);
    }

    @Override
    public EstadoActividadDomainEntity obtenerEstadoActividadPorId(String id) {
        // Lógica para obtener un estado de actividad por su ID
        return estadoActividadRepository.findById(id).orElse(null);
    }

    @Override
    public List<EstadoActividadDomainEntity> obtenerTodosLosEstadosActividad() {
        // Lógica para obtener todos los estados de actividad
        return estadoActividadRepository.findAll();
    }
    @Override
    public List<EstadoActividadDomainEntity> listarEstadosActividad() {
        return estadoActividadRepository.findAll();
    }
}