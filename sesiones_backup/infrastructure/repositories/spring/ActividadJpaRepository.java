package com.zentry.sigea.modules.sesiones.infrastructure.repositories.spring;

import com.zentry.sigea.infrastructure.database.entities.ActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadJpaRepository extends JpaRepository<ActividadEntity, Long> {
}
