package com.zentry.sigea.module_certificacion.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_certificacion.core.entities.CertificadoDomainEntity;

public interface ICertificadoRepository {
    public Optional<CertificadoDomainEntity> findById(String id);
    public void save(CertificadoDomainEntity certificadoDomainEntity);
    public Optional<CertificadoDomainEntity> findByAsistenciaId(String asistenciaId);
    public Optional<CertificadoDomainEntity> findByListActividadIds(List<String> listActividadIds);
}
