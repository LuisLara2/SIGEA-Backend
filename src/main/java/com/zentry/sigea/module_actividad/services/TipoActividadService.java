package com.zentry.sigea.module_actividad.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.core.services.usecases.crear_tipo_activiadad.CrearTipoActividadUseCase;
import com.zentry.sigea.module_actividad.infrastructure.repository.tipoactividad_repository.TipoActividadRepositoryImpl;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;

@Service
public class TipoActividadService {

    private final TipoActividadRepositoryImpl tipoactividadRepository;
    private final CrearTipoActividadUseCase crearTipoActividadUseCase;

    public TipoActividadService(TipoActividadRepositoryImpl tipoactividadRepository, CrearTipoActividadUseCase crearTipoActividadUseCase) {
        this.tipoactividadRepository = tipoactividadRepository;
        this.crearTipoActividadUseCase = crearTipoActividadUseCase;
    }

    /**
     *  Crea un nuevo tipo de actividad
     */

    public TipoActividad crearTipoActividad(TipoActividadRequest request) {
        return crearTipoActividadUseCase.execute(request);
    }

    public Optional<TipoActividad> obtenerPorId(Long id) {
        TipoActividad actividad = tipoactividadRepository.findById(id);
        return Optional.ofNullable(actividad);
    }

}