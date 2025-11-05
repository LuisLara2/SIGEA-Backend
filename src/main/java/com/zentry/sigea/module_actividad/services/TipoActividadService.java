package com.zentry.sigea.module_actividad.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.TipoActividadRequest;
import com.zentry.sigea.module_actividad.services.interfaces.ITipoActividad;
import com.zentry.sigea.module_actividad.services.usecases.tipo_actividad.CrearTipoActividadUseCase;

@Service
public class TipoActividadService implements ITipoActividad {

    private final ITipoActividadRepository tipoactividadRepository;
    private final CrearTipoActividadUseCase crearTipoActividadUseCase;

    public TipoActividadService(
        ITipoActividadRepository tipoactividadRepository, 
        CrearTipoActividadUseCase crearTipoActividadUseCase
    ) {
        this.tipoactividadRepository = tipoactividadRepository;
        this.crearTipoActividadUseCase = crearTipoActividadUseCase;
    }

    @Override
    public String crearTipoActividad(TipoActividadRequest tipoActividad) {
        return crearTipoActividadUseCase.execute(tipoActividad);
    }

    @Override
    public TipoActividadDomainEntity actualizarTipoActividad(TipoActividadRequest tipoActividad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarTipoActividad'");
    }

    @Override
    public void eliminarTipoActividad(String id) {
        tipoactividadRepository.deleteById(id);
    }

    @Override
    public TipoActividadDomainEntity obtenerTipoActividadPorId(String id) {
        // TODO Auto-generated method stub
        return tipoactividadRepository.findById(id).orElse(null);
    }

    @Override
    public List<TipoActividadDomainEntity> listarTiposActividad() {
        return tipoactividadRepository.findAll();
    }
}