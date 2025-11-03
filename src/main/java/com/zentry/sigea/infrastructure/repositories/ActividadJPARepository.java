package com.zentry.sigea.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.infrastructure.database.entities.ActividadEntity;

public interface ActividadJPARepository extends JpaRepository<ActividadEntity , UUID>{
    public Optional<ActividadEntity> findById(UUID id);
    public List<ActividadEntity> findByOrganizadorId(UUID organizadorId);
}
