package com.zentry.sigea.module_usuarios.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_usuarios.core.entities.InscripcionDomainEntity;

public interface IInscripcionRepository {
    public void save(InscripcionDomainEntity inscripcionDomainEntity);
    public Optional<InscripcionDomainEntity> findById(String id);
    public List<InscripcionDomainEntity> findByActividadId(String actividadId);
    public List<InscripcionDomainEntity> findByUsuarioId(String usuarioId);
}
