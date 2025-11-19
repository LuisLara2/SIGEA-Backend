package com.zentry.sigea.module_certificacion.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_certificacion.core.entities.CertificadoDomainEntity;
import com.zentry.sigea.module_certificacion.core.repositories.ICertificadoRepository;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.EstadoCertificadoEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.mappers.CertificadoMapper;
import com.zentry.sigea.module_certificacion.infrastructure.repositories.CertificadoJPARepository;
import com.zentry.sigea.module_certificacion.infrastructure.repositories.EstadoCertificadoJPARepository;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.AsistenciaEntity;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.AsistenciaJPARepository;

@Repository
public class CertificadoRepositoryAdapter implements ICertificadoRepository {

    private final CertificadoJPARepository certificadoJPARepository;
    
    private final AsistenciaJPARepository asistenciaJPARepository;
    private final EstadoCertificadoJPARepository estadoCertificadoJPARepository;

    public CertificadoRepositoryAdapter(
        CertificadoJPARepository certificadoJPARepository , 
        AsistenciaJPARepository asistenciaJPARepository , 
        EstadoCertificadoJPARepository estadoCertificadoJPARepository
    ){
        this.certificadoJPARepository = certificadoJPARepository;
        this.asistenciaJPARepository = asistenciaJPARepository;
        this.estadoCertificadoJPARepository = estadoCertificadoJPARepository;
    }

    public Optional<CertificadoDomainEntity> findById(String id){
        return certificadoJPARepository.findById(UUID.fromString(id))
            .map(c -> CertificadoMapper.toDomain(c));
    }

    public void save(CertificadoDomainEntity certificadoDomainEntity){
        
        AsistenciaEntity asistenciaEntity = asistenciaJPARepository.findById(
            UUID.fromString(certificadoDomainEntity.getAsistenciaId())
        ).orElse(null);

        EstadoCertificadoEntity estadoCertificadoEntity = estadoCertificadoJPARepository.findById(
            UUID.fromString(certificadoDomainEntity.getEstadoCertificadoDomainEntity().getId())
        ).orElse(null);
        
        certificadoJPARepository.save(
            CertificadoMapper.toEntity(
                certificadoDomainEntity, 
                asistenciaEntity, 
                estadoCertificadoEntity
            )
        );
    }

    public Optional<CertificadoDomainEntity> findByAsistenciaId(String asistenciaId){
        return certificadoJPARepository.findByAsistencia_Id(
            UUID.fromString(asistenciaId)
        ).map(c -> CertificadoMapper.toDomain(c));
    }

    public Optional<CertificadoDomainEntity> findByListActividadIds(List<String> listActividadIds){
        return certificadoJPARepository.findByListActividadIds(
            listActividadIds.stream()
                .map(UUID::fromString)
                .collect(Collectors.toList())
        ).map(c -> CertificadoMapper.toDomain(c));
    }
}
