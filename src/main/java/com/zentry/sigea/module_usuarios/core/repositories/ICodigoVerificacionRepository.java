package com.zentry.sigea.module_usuarios.core.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_usuarios.core.entities.CodigoVerificacionDomainEntity;

public interface ICodigoVerificacionRepository {
    public void save(CodigoVerificacionDomainEntity codigoVerificacionDomainEntity);
    public Optional<CodigoVerificacionDomainEntity> findById(String id);
    public Optional<CodigoVerificacionDomainEntity> findByCorreoAndCodigo(String correo , String codigo);
    public List<String> findCodigoByCorreo(String correo);
    public void deleteExpiresCodes(Instant now);
    public void deleteAllByCorreo(String correo);
}
