package com.zentry.sigea.module_sesiones.infrastructure.database.adapters;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_actividad.infrastructure.repository.ActividadJPARepository;
import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.core.repositories.ISesionRepository;
import com.zentry.sigea.module_sesiones.infrastructure.database.mappers.SesionMapper;
import com.zentry.sigea.module_sesiones.infrastructure.repositories.SesionJPARepository;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;

/**
 * Adaptador que implementa el repositorio del dominio usando JPA
 * Este es el puente entre el dominio y la infraestructura (Hexagonal Architecture)
 */
@Repository
public class SesionRepositoryAdapter implements ISesionRepository {

    private final SesionJPARepository sesionJPARepository;
    private final ActividadJPARepository actividadJpaRepository;

    public SesionRepositoryAdapter(
        SesionJPARepository sesionJPARepository,
        ActividadJPARepository actividadJpaRepository
    ) {
        this.sesionJPARepository = sesionJPARepository;
        this.actividadJpaRepository = actividadJpaRepository;
    }

    @Override
    public SesionDomainEntity save(SesionDomainEntity sesion) {
        if (sesion.getActividadId() == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo.");
        }

        ActividadEntity actividadEntity = actividadJpaRepository
            .findById(UUID.fromString(sesion.getActividadId()))
            .orElseThrow(() -> new IllegalArgumentException(
                "No se encontró actividad con ID: " + sesion.getActividadId()
            ));
        
        var sesionEntity = SesionMapper.toEntity(sesion, actividadEntity);
        var savedEntity = sesionJPARepository.save(sesionEntity);
    
        return SesionMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SesionDomainEntity> findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        return sesionJPARepository
            .findById(UUID.fromString(id))
            .map(SesionMapper::toDomain);
    }

    @Override
    public List<SesionDomainEntity> findAll() {
        return sesionJPARepository.findAll()
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<SesionDomainEntity> findByActividadId(String actividadId) {
        if (actividadId == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo.");
        }
        return sesionJPARepository.findByActividadId(UUID.fromString(actividadId))
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<SesionDomainEntity> findByFechaRange(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas.");
        }
        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
        
        return sesionJPARepository.findByFechaSesionBetween(inicio, fin)
            .stream()
            .filter(sesionEntity -> {
                // Validar que la sesión esté dentro del rango de fechas de su actividad
                Optional<ActividadEntity> actividadOpt = sesionEntity.getActividad() != null
                    ? Optional.of(sesionEntity.getActividad())
                    : Optional.empty();
                    
                if (actividadOpt.isPresent()) {
                    ActividadEntity actividad = actividadOpt.get();
                    // Convertir LocalDate a LocalDateTime para comparación
                    LocalDateTime fechaInicioActividad = actividad.getFechaInicio().atStartOfDay();
                    LocalDateTime fechaFinActividad = actividad.getFechaFin().atTime(23, 59, 59);
                    
                    return !sesionEntity.getFechaSesion().isBefore(fechaInicioActividad) &&
                           !sesionEntity.getFechaSesion().isAfter(fechaFinActividad);
                }
                return false;
            })
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        return sesionJPARepository.existsById(UUID.fromString(id));
    }

    @Override
    public void deleteById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo.");
        }
        sesionJPARepository.deleteById(UUID.fromString(id));
    }

    @Override
    public long countByActividadId(String actividadId) {
        if (actividadId == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo.");
        }
        return sesionJPARepository.countByActividadId(UUID.fromString(actividadId));
    }

    @Override
    public List<SesionDomainEntity> findByPonente(String ponente) {
        if (ponente == null || ponente.isEmpty()) {
            throw new IllegalArgumentException("El nombre del ponente no puede ser nulo o vacío.");
        }
        return sesionJPARepository.findByPonente(ponente)
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<SesionDomainEntity> findByTituloContaining(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }
        return sesionJPARepository.findByTituloContaining(titulo)
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<SesionDomainEntity> findByModalidad(Modalidad modalidad) {
        if (modalidad == null) {
            throw new IllegalArgumentException("La modalidad no puede ser nula.");
        }
        return sesionJPARepository.findByModalidad(modalidad)
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<SesionDomainEntity> findByActividadIdAndModalidad(UUID actividadId, Modalidad modalidad) {
        if (actividadId == null) {
            throw new IllegalArgumentException("El ID de la actividad no puede ser nulo.");
        }
        if (modalidad == null) {
            throw new IllegalArgumentException("La modalidad no puede ser nula.");
        }

        return sesionJPARepository.findByActividadIdAndModalidad(actividadId, modalidad)
            .stream()
            .map(SesionMapper::toDomain)
            .collect(Collectors.toList());
    }

    public Optional<SesionDomainEntity> findByOrdenContainingAndActividad_Id(String orden , String actividadId){
        return sesionJPARepository.findByOrdenContainingAndActividad_Id(orden, UUID.fromString(actividadId))
            .map(s -> SesionMapper.toDomain(s));
    }
}