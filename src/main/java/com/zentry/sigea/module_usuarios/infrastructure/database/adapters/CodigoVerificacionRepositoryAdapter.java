package com.zentry.sigea.module_usuarios.infrastructure.database.adapters;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zentry.sigea.module_usuarios.core.entities.CodigoVerificacionDomainEntity;
import com.zentry.sigea.module_usuarios.core.repositories.ICodigoVerificacionRepository;
import com.zentry.sigea.module_usuarios.infrastructure.database.mappers.CodigoVerificacionMapper;
import com.zentry.sigea.module_usuarios.infrastructure.repositories.CodigoVerificacionJPARepository;

@Repository
public class CodigoVerificacionRepositoryAdapter implements ICodigoVerificacionRepository {
    
    private final CodigoVerificacionJPARepository codigoVerificacionJPARepository;

    public CodigoVerificacionRepositoryAdapter(
        CodigoVerificacionJPARepository codigoVerificacionJPARepository
    ){
        this.codigoVerificacionJPARepository = codigoVerificacionJPARepository;
    }

    public void save(CodigoVerificacionDomainEntity codigoVerificacionDomainEntity){
        codigoVerificacionJPARepository.saveAndFlush(
            CodigoVerificacionMapper.toEntity(codigoVerificacionDomainEntity)
        );
    }

    public Optional<CodigoVerificacionDomainEntity> findById(String id){
        return codigoVerificacionJPARepository.findById(UUID.fromString(id))
            .map(cv -> CodigoVerificacionMapper.toDomain(cv));
    }

    public Optional<CodigoVerificacionDomainEntity> findByCorreoAndCodigo(String correo , String codigo){
        return codigoVerificacionJPARepository.findByCorreoAndCodigo(correo, codigo)
            .map(cv -> CodigoVerificacionMapper.toDomain(cv));
    }

    public List<String> findCodigoByCorreo(String correo){
        return codigoVerificacionJPARepository.findCodigoByCorreo(correo);
    }

    @Transactional
    public void deleteExpiresCodes(Instant now){
        try {
            codigoVerificacionJPARepository.deleteExpiredCodes(now);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void deleteAllByCorreo(String correo){
        codigoVerificacionJPARepository.deleteAllByCorreo(correo);
    }
}
