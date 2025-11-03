package com.zentry.sigea.module_actividad.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;
import com.zentry.sigea.module_actividad.infrastructure.repository.tipoactividad_repository.TipoActividadRepositoryImpl;
import com.zentry.sigea.module_actividad.presentation.models.TipoActividadRequest;
import com.zentry.sigea.module_actividad.services.interfaces.ITipoActividad;
import com.zentry.sigea.module_actividad.services.usecases.tipo_actividad.CrearTipoActividadUseCase;

@Service
public class TipoActividadService implements ITipoActividad {

    private final TipoActividadRepositoryImpl tipoactividadRepository;
    private final CrearTipoActividadUseCase crearTipoActividadUseCase;

    public TipoActividadService(TipoActividadRepositoryImpl tipoactividadRepository, CrearTipoActividadUseCase crearTipoActividadUseCase) {
        this.tipoactividadRepository = tipoactividadRepository;
        this.crearTipoActividadUseCase = crearTipoActividadUseCase;
    }

    @Override
    public TipoActividad crearTipoActividad(TipoActividadRequest tipoActividad) {
        return crearTipoActividadUseCase.execute(tipoActividad);
    }

    @Override
    public TipoActividad actualizarTipoActividad(TipoActividadRequest tipoActividad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarTipoActividad'");
    }

    @Override
    public void eliminarTipoActividad(Long id) {
        tipoactividadRepository.deleteById(id);
    }

    @Override
    public TipoActividad obtenerTipoActividadPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTipoActividadPorId'");
    }

    @Override
    public List<TipoActividad> listarTiposActividad() {
        return tipoactividadRepository.findAll();
    }
}