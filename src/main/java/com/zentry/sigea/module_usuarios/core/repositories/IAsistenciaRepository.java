package com.zentry.sigea.module_usuarios.core.repositories;

import java.util.List;
import java.util.Optional;

import com.zentry.sigea.module_usuarios.core.entities.AsistenciaDomainEntity;

public interface IAsistenciaRepository {
    public void save(AsistenciaDomainEntity asistenciaDomainEntity);
    public Optional<AsistenciaDomainEntity> findById(String id);
    public List<AsistenciaDomainEntity> findByInscripcionId(String inscripcionId);
    public List<AsistenciaDomainEntity> findBySesionId(String sesionId);
    public List<AsistenciaDomainEntity> findBySesionIdAndPresente(String sesionId , Boolean presente);
}