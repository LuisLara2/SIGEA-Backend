package com.zentry.sigea.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.core.entities.InscripcionDomainEntity;

public interface IInscripcionRepository {
    public void save(InscripcionDomainEntity inscripcionDomainEntity);
    public Optional<InscripcionDomainEntity> findById(String id);
    public List<InscripcionDomainEntity> findByActividadId(String actividadId);
    public List<InscripcionDomainEntity> findByUsuarioId(String usuarioId);
}
