package com.zentry.sigea.module_certificacion.infrastructure.database.adapters;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_certificacion.core.entities.ValidacionDomainEntity;
import com.zentry.sigea.module_certificacion.core.repositories.IValidacionRepository;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.CertificadoEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.entities.TipoValidadorEntity;
import com.zentry.sigea.module_certificacion.infrastructure.database.mappers.ValidacionMapper;
import com.zentry.sigea.module_certificacion.infrastructure.repositories.CertificadoJPARepository;
import com.zentry.sigea.module_certificacion.infrastructure.repositories.TipoValidadorJPARepository;
import com.zentry.sigea.module_certificacion.infrastructure.repositories.ValidacionJPARepository;

@Repository
public class ValidacionRepositoryAdapter implements IValidacionRepository {
    
    private final ValidacionJPARepository validacionJPARepository;

    private final CertificadoJPARepository certificadoJPARepository;
    private final TipoValidadorJPARepository tipoValidadorJPARepository;

    public ValidacionRepositoryAdapter(
        ValidacionJPARepository validacionJPARepository , 
        CertificadoJPARepository certificadoJPARepository , 
        TipoValidadorJPARepository tipoValidadorJPARepository
    ){
        this.validacionJPARepository = validacionJPARepository;
        this.certificadoJPARepository = certificadoJPARepository;
        this.tipoValidadorJPARepository = tipoValidadorJPARepository;
    }

    public Optional<ValidacionDomainEntity> findById(String id){
        return validacionJPARepository.findById(UUID.fromString(id))
            .map(v -> ValidacionMapper.toDomain(v));
    }

    public Optional<ValidacionDomainEntity> findByCertificadoId(String certificadoId){
        return validacionJPARepository.findByCertificado_Id(
            UUID.fromString(certificadoId)
        ).map(v -> ValidacionMapper.toDomain(v));
    }

    public void save(ValidacionDomainEntity validacionDomainEntity){
        
        CertificadoEntity certificadoEntity = certificadoJPARepository.findById(
            UUID.fromString(validacionDomainEntity.getCertificadoId())
        ).orElse(null);

        TipoValidadorEntity tipoValidadorEntity = tipoValidadorJPARepository.findById(
            UUID.fromString(validacionDomainEntity.getTipoValidadorDomainEntity().getId())
        ).orElse(null);

        validacionJPARepository.save(
            ValidacionMapper.toEntity(
                validacionDomainEntity, 
                certificadoEntity, 
                tipoValidadorEntity
            )
        );
    }

    public void saveAll(List<ValidacionDomainEntity> listValidacionDomainEntities){
        validacionJPARepository.saveAll(
            listValidacionDomainEntities.stream()
            .map(
                (v) -> {
                    CertificadoEntity certificadoEntity = certificadoJPARepository.findById(
                        UUID.fromString(v.getCertificadoId())
                    ).orElse(null);

                    TipoValidadorEntity tipoValidadorEntity = tipoValidadorJPARepository.findById(
                        UUID.fromString(v.getTipoValidadorDomainEntity().getId())
                    ).orElse(null);

                    return ValidacionMapper.toEntity(
                        v, 
                        certificadoEntity, 
                        tipoValidadorEntity
                    );
                }
            )
            .collect(Collectors.toList())
        );
    }
}
