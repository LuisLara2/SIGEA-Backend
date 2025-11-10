package com.zentry.sigea.module_sesiones.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity;

public interface SesionJPARepository extends JpaRepository<SesionEntity , UUID>{
    
}
