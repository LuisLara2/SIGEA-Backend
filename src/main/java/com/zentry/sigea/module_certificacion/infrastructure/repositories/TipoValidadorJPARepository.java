package com.zentry.sigea.module_certificacion.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zentry.sigea.module_certificacion.infrastructure.database.entities.TipoValidadorEntity;

public interface TipoValidadorJPARepository extends JpaRepository<TipoValidadorEntity , UUID>{
    
}
