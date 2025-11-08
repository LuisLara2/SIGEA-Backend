package com.zentry.sigea.module_usuarios.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_actividad.infrastructure.repository.ActividadJPARepository;
import com.zentry.sigea.module_usuarios.core.entities.InscripcionDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IInscripcionRepository;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.EstadoInscripcionEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.mappers.InscripcionMapper;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.EstadoInscripcionJPARepository;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.InscripcionJPARepository;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.UsuarioJPARepository;

@Repository
public class InscripcionRepositoryAdapter implements IInscripcionRepository {
    
    private final InscripcionJPARepository inscripcionJPARepository;

    private final UsuarioJPARepository usuarioJPARepository;
    private final ActividadJPARepository actividadJPARepository;
    private final EstadoInscripcionJPARepository estadoInscripcionJPARepository;

    public InscripcionRepositoryAdapter(
        InscripcionJPARepository inscripcionJPARepository,
        UsuarioJPARepository usuarioJPARepository,
        ActividadJPARepository actividadJPARepository,
        EstadoInscripcionJPARepository estadoInscripcionJPARepository
    ){
        this.inscripcionJPARepository = inscripcionJPARepository;
        this.usuarioJPARepository = usuarioJPARepository;
        this.actividadJPARepository = actividadJPARepository;
        this.estadoInscripcionJPARepository = estadoInscripcionJPARepository;
    }

    public void save(InscripcionDomainEntity inscripcionDomainEntity){
        UsuarioEntity usuarioEntity = usuarioJPARepository.findById(
            UUID.fromString(inscripcionDomainEntity.getUsuarioId())
        ).orElse(null);

        ActividadEntity actividadEntity = actividadJPARepository.findById(
            UUID.fromString(inscripcionDomainEntity.getActividadId())
        ).orElse(null);

        EstadoInscripcionEntity estadoInscripcionEntity = estadoInscripcionJPARepository.findById(
            UUID.fromString(inscripcionDomainEntity.getEstadoInscripcionId())
        ).orElse(null);

        inscripcionJPARepository.save(
            InscripcionMapper.toEntity(
                inscripcionDomainEntity, 
                usuarioEntity, 
                actividadEntity, 
                estadoInscripcionEntity
            )
        );
    }

    public Optional<InscripcionDomainEntity> findById(String id){
        return inscripcionJPARepository.findById(
            UUID.fromString(id)
        ).map(i -> InscripcionMapper.toDomain(i));
    }

    public List<InscripcionDomainEntity> findByActividadId(String actividadId){
        return inscripcionJPARepository.findByActividadId(
            UUID.fromString(actividadId)
        ).stream()
        .map(InscripcionMapper::toDomain)
        .collect(Collectors.toList());
    }

    public List<InscripcionDomainEntity> findByUsuarioId(String usuarioId){
        return inscripcionJPARepository.findByUsuarioId(
            UUID.fromString(usuarioId)
        ).stream()
        .map(InscripcionMapper::toDomain)
        .collect(Collectors.toList());
    }
}
