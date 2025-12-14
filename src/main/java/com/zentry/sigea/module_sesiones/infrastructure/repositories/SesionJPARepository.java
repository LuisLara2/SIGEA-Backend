package com.zentry.sigea.module_sesiones.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Repositorio JPA de Spring Data para SesionEntity.
 * Proporciona métodos CRUD y consultas personalizadas para la entidad SesionEntity.
 */
@Repository
public interface SesionJPARepository extends JpaRepository<SesionEntity, UUID> {
    /**
     * Encuentra todas las sesiones asociadas a una actividad específica.
     */
    List<SesionEntity> findByActividadId(UUID actividadId);

    /**
     * Encuentra todas las sesiones dentro de un rango de fechas.
     */
    @Query("SELECT s FROM SesionEntity s WHERE s.fechaSesion BETWEEN :inicio AND :fin")
    List<SesionEntity> findByFechaSesionBetween(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Cuenta el número de sesiones asociadas a una actividad específica.
     */
    long countByActividadId(UUID actividadId);

    /**
     * Verifica si existe una sesión con una actividad y fecha específicas.
     */
    boolean existsByActividadIdAndFechaSesion(UUID actividadId, LocalDateTime fechaSesion);

    /**
     * Encuentra todas las sesiones asociadas a un ponente específico.
     * 
     * @param ponente El nombre del ponente.
     * @return Una lista de sesiones asociadas al ponente.
     */
    List<SesionEntity> findByPonente(String ponente);

    /**
     * Encuentra todas las sesiones cuyo título contenga una cadena específica.
     * 
     * @param titulo La cadena a buscar en los títulos.
     * @return Una lista de sesiones cuyo título contiene la cadena.
     */
    List<SesionEntity> findByTituloContaining(String titulo);

    /**
     * Encuentra todas las sesiones por modalidad.
     * 
     * @param modalidad La modalidad de las sesiones (PRESENCIAL, VIRTUAL, HIBRIDA).
     * @return Una lista de sesiones con la modalidad especificada.
     */
    List<SesionEntity> findByModalidad(Modalidad modalidad);

    List<SesionEntity> findByActividadIdAndModalidad(UUID actividadId, Modalidad modalidad);

    /**
     * Elimina todas las sesiones de una actividad específica
     */
    void deleteByActividadId(UUID actividadId);
}
