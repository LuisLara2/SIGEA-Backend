package com.zentry.sigea.module_usuarios.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_inscripciones.infrastructure.database.entities.InscripcionEntity;
import com.zentry.sigea.module_inscripciones.infrastructure.repository.InscripcionJPARepository;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity;
import com.zentry.sigea.module_sesiones.infrastructure.repositories.SesionJPARepository;
import com.zentry.sigea.module_usuarios.core.entities.AsistenciaDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.IAsistenciaRepository;
import com.zentry.sigea.module_usuarios.infrastructure.database.mappers.AsistenciaMapper;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.AsistenciaJPARepository;

@Repository
public class AsistenciaRepositoryAdapter implements IAsistenciaRepository{
    
    private final AsistenciaJPARepository asistenciaJPARepository;
    
    private final SesionJPARepository sesionJPARepository;
    private final InscripcionJPARepository inscripcionJPARepository;

    public AsistenciaRepositoryAdapter(
        AsistenciaJPARepository asistenciaJPARepository , 
        SesionJPARepository sesionJPARepository,
        InscripcionJPARepository inscripcionJPARepository
    ){
        this.asistenciaJPARepository = asistenciaJPARepository;
        this.sesionJPARepository = sesionJPARepository;
        this.inscripcionJPARepository = inscripcionJPARepository;
    }
    public void save(AsistenciaDomainEntity asistenciaDomainEntity){
        
        InscripcionEntity inscripcionEntity = inscripcionJPARepository.findById(
            UUID.fromString(asistenciaDomainEntity.getInscripcionId())
        ).orElse(null);

        SesionEntity sesionEntity = sesionJPARepository.findById(
            UUID.fromString(asistenciaDomainEntity.getSesionId())
        ).orElse(null);

        asistenciaJPARepository.save(
            AsistenciaMapper.toEntity(
                asistenciaDomainEntity , 
                sesionEntity , 
                inscripcionEntity
            )
        );
    }

    public Optional<AsistenciaDomainEntity> findById(String id){
        return asistenciaJPARepository.findById(UUID.fromString(id))
            .map(a -> AsistenciaMapper.toDomain(a));
    }

    public List<AsistenciaDomainEntity> findByInscripcionId(String inscripcionId){
        return asistenciaJPARepository.findByInscripcionId(UUID.fromString(inscripcionId))
            .stream()
            .map(AsistenciaMapper::toDomain)
            .collect(Collectors.toList());
    }

    public List<AsistenciaDomainEntity> findBySesionId(String sesionId){
        return asistenciaJPARepository.findBySesionId(UUID.fromString(sesionId))
            .stream()
            .map(AsistenciaMapper::toDomain)
            .collect(Collectors.toList());
    }

    public List<AsistenciaDomainEntity> findBySesionIdAndPresente(String sesionId , Boolean presente){
        return asistenciaJPARepository.findBySesionIdAndPresente(UUID.fromString(sesionId), presente)
            .stream()
            .map(AsistenciaMapper::toDomain)
            .collect(Collectors.toList());
    }
}
