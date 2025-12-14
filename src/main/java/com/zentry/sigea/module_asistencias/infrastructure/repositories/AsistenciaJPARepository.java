package com.zentry.sigea.module_asistencias.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zentry.sigea.module_asistencias.infrastructure.database.entities.AsistenciaEntity;

public interface AsistenciaJPARepository extends JpaRepository<AsistenciaEntity, UUID>{
    Optional<AsistenciaEntity> findById(UUID id);
    List<AsistenciaEntity> findByInscripcionId(UUID inscripcionId);
    List<AsistenciaEntity> findBySesionId(UUID sesionId);
    List<AsistenciaEntity> findBySesionIdAndPresente(UUID sesionId, Boolean presente);


    @Query(
        """
            SELECT a.id FROM AsistenciaEntity a
            WHERE a.inscripcion.id = :inscripcionId
        """
    )
    public List<UUID> findIdsByInscripcionId(
        @Param("inscripcionId") UUID inscripcionId
    );

    @Query(
        """
            SELECT a.presente 
            FROM AsistenciaEntity a
            WHERE a.sesionId = :sesionId AND a.inscripcionId = :inscripcionId
        """
    )
    public Boolean findPresenteBySesionIdAndInscripcionId(
        @Param("sesionId") UUID sesionId,
        @Param("inscripcionId") UUID inscripcionId
    );

    // Siempre usa count no size() ya que el ultimo es menos eficiente
    public long countBySesion_Id(UUID sesionId);
}
