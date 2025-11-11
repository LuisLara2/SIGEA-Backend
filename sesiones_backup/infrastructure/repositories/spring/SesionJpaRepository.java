package com.zentry.sigea.modules.sesiones.infrastructure.repositories.spring;

import com.zentry.sigea.infrastructure.database.entities.SesionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionJpaRepository extends JpaRepository<SesionEntity, Long> {
}
