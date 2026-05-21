package com.zentry.sigea.module_sesiones.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_inscripciones.core.repositories.IInscripcionRepository;
import com.zentry.sigea.module_notificaciones.events.domain.SesionCreadaEvent;
import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;
import com.zentry.sigea.module_sesiones.presentacion.models.CrearSesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionRequest;
import com.zentry.sigea.module_sesiones.presentacion.models.SesionResponse;
import com.zentry.sigea.module_sesiones.services.interfaces.ISesionService;
import com.zentry.sigea.module_sesiones.services.usecases.sesion.ActualizarSesionUseCase;
import com.zentry.sigea.module_sesiones.services.usecases.sesion.CrearSesionUseCase;
import com.zentry.sigea.module_sesiones.services.usecases.sesion.EliminarSesionUseCase;
import com.zentry.sigea.module_sesiones.services.usecases.sesion.ListarSesionesUseCase;



/**
 * Servicio de aplicación para Sesiones
 * Orquesta los casos de uso y maneja transacciones
 */
@Service
@Transactional
public class SesionService implements ISesionService {

    private final CrearSesionUseCase crearSesionUseCase;
    private final ListarSesionesUseCase listarSesionesUseCase;
    private final ActualizarSesionUseCase actualizarSesionUseCase;
    private final EliminarSesionUseCase eliminarSesionUseCase;
    private final ApplicationEventPublisher eventPublisher;
    private final IInscripcionRepository inscripcionRepository;
    

    public SesionService(
        CrearSesionUseCase crearSesionUseCase,
        ListarSesionesUseCase listarSesionesUseCase,
        ActualizarSesionUseCase actualizarSesionUseCase,
        EliminarSesionUseCase eliminarSesionUseCase,
        ApplicationEventPublisher eventPublisher,
        IInscripcionRepository inscripcionRepository
    ) {
        this.crearSesionUseCase = crearSesionUseCase;
        this.listarSesionesUseCase = listarSesionesUseCase;
        this.actualizarSesionUseCase = actualizarSesionUseCase;
        this.eliminarSesionUseCase = eliminarSesionUseCase;
        this.eventPublisher = eventPublisher;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public SesionResponse crearSesion(CrearSesionRequest request) {
        try {
            SesionDomainEntity sesionCreada = crearSesionUseCase.execute(request);

            if (sesionCreada == null || sesionCreada.getId() == null) {
                throw new IllegalStateException("La sesión no fue creada correctamente - ID es null");
            }

            List<String> usuariosInscritos = inscripcionRepository
                .findByActividadId(request.getActividadId())
                .stream()
                .map(inscripcion -> inscripcion.getUsuarioId())
                .collect(Collectors.toList());

            if (!usuariosInscritos.isEmpty()) {
                eventPublisher.publishEvent(new SesionCreadaEvent(
                    sesionCreada.getId(),
                    request.getActividadId(),
                    sesionCreada.getTitulo(),
                    sesionCreada.getFechaSesion(),
                    usuariosInscritos,
                    LocalDateTime.now(),
                    sesionCreada.getLugarSesion(),
                    sesionCreada.getPonente(),
                    sesionCreada.getModalidad() != null ? sesionCreada.getModalidad().name() : null,
                    sesionCreada.getLinkVirtual(),
                    sesionCreada.getHoraInicio(),
                    sesionCreada.getHoraFin(),
                    sesionCreada.getDescripcion()
                ));
            }

            return SesionResponse.fromDomain(sesionCreada);
        } catch (Exception e) {
            throw new SesionCreationException("Error al crear sesión: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SesionResponse> listarSesiones() {
        return listarSesionesUseCase.execute()
            .stream()
            .map(SesionResponse::fromDomain)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SesionResponse> listarSesionesPorActividad(String actividadId) {
        if (actividadId == null || actividadId.isEmpty()) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo o vacío.");
        }

        return listarSesionesUseCase.executeByActividad(actividadId)
            .stream()
            .map(SesionResponse::fromDomain)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SesionResponse obtenerSesionPorId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID debe ser un número positivo");
        }

        return listarSesionesUseCase.execute()
            .stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .map(SesionResponse::fromDomain)
            .orElseThrow(() -> new IllegalArgumentException("Sesión no encontrada con ID: " + id));
    }

    @Override
    public SesionResponse actualizarSesion(String id, SesionRequest request) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID de la sesión no puede ser nulo o vacío.");
        }

        if (request.getTitulo() == null || request.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }

        SesionEntity.Modalidad modalidad = SesionEntity.Modalidad.valueOf(request.getModalidad().toUpperCase());

        // Crear entidad con los datos actualizados del request
        SesionDomainEntity datosActualizados = SesionDomainEntity.reconstruct(
            null, // id - no necesario para actualizar
            null, // actividadId - se mantiene el existente en updateInfo
            request.getTitulo(),
            request.getDescripcion(),
            request.getFechaSesion(),
            request.getHoraInicio(),
            request.getHoraFin(),
            request.getPonente(),
            modalidad,
            request.getLugarSesion(),
            request.getLinkVirtual(),
            request.getOrden(),
            null, // createdAt - no se modifica
            null  // updatedAt - se actualiza en updateInfo
        );

        return actualizarSesionUseCase.execute(id, datosActualizados)
            .map(SesionResponse::fromDomain)
            .orElseThrow(() -> new IllegalArgumentException("Sesión no encontrada con ID: " + id));
    }

    @Override
    public void eliminarSesion(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID de la sesión no puede ser nulo o vacío.");
        }

        eliminarSesionUseCase.execute(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SesionResponse> listarSesiones(String actividadId, Modalidad modalidad) {
        if (actividadId == null && modalidad == null) {
            // Si no hay filtros, listar todas las sesiones
            return listarSesiones();
        }

        if (actividadId != null && modalidad != null) {
            // Si ambos filtros están presentes, listar por actividad y modalidad
            return listarSesionesUseCase.executeByActividadAndModalidad(actividadId, modalidad)
                .stream()
                .map(SesionResponse::fromDomain)
                .collect(Collectors.toList());
        }

        if (actividadId != null) {
            // Si solo se filtra por actividad
            return listarSesionesPorActividad(actividadId);
        }

        // Si solo se filtra por modalidad
        return listarSesionesUseCase.executeByModalidad(modalidad)
            .stream()
            .map(SesionResponse::fromDomain)
            .collect(Collectors.toList());
    }

    public class SesionCreationException extends RuntimeException {
        public SesionCreationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}