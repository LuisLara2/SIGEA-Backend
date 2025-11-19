package com.zentry.sigea.module_certificacion.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zentry.sigea.module_certificacion.infrastructure.database.entities.CertificadoEntity;

public interface CertificadoJPARepository extends JpaRepository<CertificadoEntity , UUID>{
    public Optional<CertificadoEntity> findByAsistencia_Id(UUID asistenciaId);

    @Query(
        """
            SELECT c FROM CertificadoEntity c
            WHERE c.id IN :listActividadIds
        """
    )
    public Optional<CertificadoEntity> findByListActividadIds(
        @Param("listActividadIds") List<UUID> listActividadIds
    );
}
