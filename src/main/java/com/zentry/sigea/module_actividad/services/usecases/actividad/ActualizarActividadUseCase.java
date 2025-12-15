package com.zentry.sigea.module_actividad.services.usecases.actividad;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.zentry.sigea.module_actividad.core.entities.ActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.EstadoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.entities.TipoActividadDomainEntity;
import com.zentry.sigea.module_actividad.core.repositories.IActividadRespository;
import com.zentry.sigea.module_actividad.core.repositories.IEstadoActividadRepository;
import com.zentry.sigea.module_actividad.core.repositories.ITipoActividadRepository;
import com.zentry.sigea.module_actividad.presentation.models.requestDTO.CrearActividadRequest;


@Component
public class ActualizarActividadUseCase {

    private final IActividadRespository actividadRepository;
    private final IEstadoActividadRepository estadoActividadRepository;
    private final ITipoActividadRepository tipoActividadRepository;

    public ActualizarActividadUseCase(
            IActividadRespository actividadRepository,
            IEstadoActividadRepository estadoActividadRepository,
            ITipoActividadRepository tipoActividadRepository) {
        this.actividadRepository = actividadRepository;
        this.estadoActividadRepository = estadoActividadRepository;
        this.tipoActividadRepository = tipoActividadRepository;
    }

    /**
     * Valida si un string es un UUID válido
     */
    private boolean isValidUUID(String str) {
        if (str == null || str.isBlank() || str.equals("string")) {
            return false;
        }
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public ActividadDomainEntity execute(String id, CrearActividadRequest request) {
        Optional<ActividadDomainEntity> actividadOpt = actividadRepository.findById(id);
        if (actividadOpt.isEmpty()) {
            throw new IllegalArgumentException("Actividad no encontrada con ID: " + id);
        }

        ActividadDomainEntity actividad = actividadOpt.get();

        // Actualizar todos los campos de información (solo los no nulos y válidos)
        actividad.updateInfo(
                "string".equals(request.getTitulo()) ? null : request.getTitulo(),
                "string".equals(request.getDescripcion()) ? null : request.getDescripcion(),
                request.getFechaInicio(),
                request.getFechaFin(),
                request.getHoraInicio(),
                request.getHoraFin(),
                "string".equals(request.getUbicacion()) ? null : request.getUbicacion(),
                "string".equals(request.getCoOrganizador()) ? null : request.getCoOrganizador(),
                "string".equals(request.getSponsor()) ? null : request.getSponsor(),
                "string".equals(request.getBannerUrl()) ? null : request.getBannerUrl(),
                "string".equals(request.getNumeroYape()) ? null : request.getNumeroYape());

        // Buscar y asignar estado si se proporciona un estadoId válido
        if (isValidUUID(request.getEstadoId())) {
            Optional<EstadoActividadDomainEntity> estadoOpt = estadoActividadRepository.findById(request.getEstadoId());
            if (estadoOpt.isEmpty()) {
                throw new IllegalArgumentException("Estado no encontrado con ID: " + request.getEstadoId());
            }
            actividad.changeStatus(estadoOpt.get());
        }

        // Buscar y asignar tipo de actividad si se proporciona un tipoActividadId válido
        if (isValidUUID(request.getTipoActividadId())) {
            Optional<TipoActividadDomainEntity> tipoOpt = tipoActividadRepository.findById(request.getTipoActividadId());
            if (tipoOpt.isEmpty()) {
                throw new IllegalArgumentException("Tipo de actividad no encontrado con ID: " + request.getTipoActividadId());
            }
            actividad.setTipoActividadDomainEntity(tipoOpt.get());
        }

        actividadRepository.save(actividad);
        return actividad;
    }
}
