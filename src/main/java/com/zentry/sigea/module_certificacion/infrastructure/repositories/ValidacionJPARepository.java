package com.zentry.sigea.module_certificacion.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_certificacion.infrastructure.database.entities.ValidacionEntity;

public interface ValidacionJPARepository extends JpaRepository<ValidacionEntity , UUID>{
    public Optional<ValidacionEntity> findByCertificado_Id(UUID certificadoId);
}
