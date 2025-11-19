package com.zentry.sigea.module_certificacion.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_certificacion.infrastructure.database.entities.EstadoCertificadoEntity;

public interface EstadoCertificadoJPARepository extends JpaRepository<EstadoCertificadoEntity , UUID>{
    
}
