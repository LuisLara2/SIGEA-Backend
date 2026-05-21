package com.zentry.sigea.module_actividad.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.TipoActividadRequest;
import com.zentry.sigea.module_actividad.services.interfaces.ITipoActividad;
import com.zentry.sigea.module_actividad.services.usecases.tipo_actividad.ActualizarTipoActividadUseCase;
import com.zentry.sigea.module_actividad.services.usecases.tipo_actividad.CrearTipoActividadUseCase;
import com.zentry.sigea.module_actividad.services.usecases.tipo_actividad.EliminarTipoActividadUseCase;

@Service
public class TipoActividadService implements ITipoActividad {

    private final ITipoActividadRepository tipoactividadRepository;
    private final CrearTipoActividadUseCase crearTipoActividadUseCase;
    private final ActualizarTipoActividadUseCase actualizarTipoActividadUseCase;
    private final EliminarTipoActividadUseCase eliminarTipoActividadUseCase;

    public TipoActividadService(
            ITipoActividadRepository tipoactividadRepository,
            CrearTipoActividadUseCase crearTipoActividadUseCase,
            ActualizarTipoActividadUseCase actualizarTipoActividadUseCase,
            EliminarTipoActividadUseCase eliminarTipoActividadUseCase) {
        this.tipoactividadRepository = tipoactividadRepository;
        this.crearTipoActividadUseCase = crearTipoActividadUseCase;
        this.actualizarTipoActividadUseCase = actualizarTipoActividadUseCase;
        this.eliminarTipoActividadUseCase = eliminarTipoActividadUseCase;
    }

    @Override
    public String crearTipoActividad(TipoActividadRequest tipoActividad) {
        return crearTipoActividadUseCase.execute(tipoActividad);
    }

    @Override
    @Transactional
    public TipoActividadDomainEntity actualizarTipoActividad(String id, TipoActividadRequest tipoActividad) {
        return actualizarTipoActividadUseCase.execute(id, tipoActividad);
    }

    @Override
    public void eliminarTipoActividad(String id) {
        eliminarTipoActividadUseCase.execute(id);
    }

    @Override
    public TipoActividadDomainEntity obtenerTipoActividadPorId(String id) {
        return tipoactividadRepository.findById(id).orElse(null);
    }

    @Override
    public List<TipoActividadDomainEntity> listarTiposActividad() {
        return tipoactividadRepository.findAll();
    }
}