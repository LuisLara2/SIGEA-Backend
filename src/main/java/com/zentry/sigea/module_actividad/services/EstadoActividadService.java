package com.zentry.sigea.module_actividad.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.EstadoActividadRequest;
import com.zentry.sigea.module_actividad.services.interfaces.IEstadoActividad;
import com.zentry.sigea.module_actividad.services.usecases.estado_actividad.ActualizarEstadoActividadUseCase;
import com.zentry.sigea.module_actividad.services.usecases.estado_actividad.CrearEstadoActividadUseCase;
import com.zentry.sigea.module_actividad.services.usecases.estado_actividad.EliminarEstadoActividadUseCase;

@Service
public class EstadoActividadService implements IEstadoActividad {

    private final IEstadoActividadRepository estadoActividadRepository;
    private final CrearEstadoActividadUseCase crearEstadoActividadUseCase;
    private final EliminarEstadoActividadUseCase eliminarEstadoActividadUseCase;
    private final ActualizarEstadoActividadUseCase actualizarEstadoActividadUseCase;

    
    // Constructor para inyección de dependencias
    public EstadoActividadService(
        IEstadoActividadRepository estadoActividadRepository,
        CrearEstadoActividadUseCase crearEstadoActividadUseCase,
        EliminarEstadoActividadUseCase eliminarEstadoActividadUseCase,
        ActualizarEstadoActividadUseCase actualizarEstadoActividadUseCase
    ) {
        this.estadoActividadRepository = estadoActividadRepository;
        this.crearEstadoActividadUseCase = crearEstadoActividadUseCase;
        this.eliminarEstadoActividadUseCase = eliminarEstadoActividadUseCase;
        this.actualizarEstadoActividadUseCase = actualizarEstadoActividadUseCase;
    }

    @Override
    public String crearEstadoActividad(EstadoActividadRequest estadoActividad) {
        // Lógica para crear un nuevo estado de actividad
        return crearEstadoActividadUseCase.execute(estadoActividad);
    }

    @Override
    public EstadoActividadDomainEntity actualizarEstadoActividad(EstadoActividadRequest estadoActividad) {
        // Lógica para actualizar un estado de actividad existente
        return actualizarEstadoActividadUseCase.execute(estadoActividad.getCodigo(), estadoActividad);
    }

    @Override
    public void eliminarEstadoActividad(String id) {
        // Lógica para eliminar un estado de actividad por su ID
        eliminarEstadoActividadUseCase.execute(id);
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