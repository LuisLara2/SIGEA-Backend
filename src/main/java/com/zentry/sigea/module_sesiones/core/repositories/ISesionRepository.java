package com.zentry.sigea.module_sesiones.core.repositories;

import com.zentry.sigea.module_sesiones.core.entities.SesionDomainEntity;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;

import java.lang.StackWalker.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interfaz del repositorio de sesiones
 * Define el contrato que debe implementar la infraestructura
 */
public interface ISesionRepository {
    
    /**
     * Guarda una sesión en el repositorio.
     * @param sesion La sesión a guardar.
     * @return La sesión guardada.
     */
    SesionDomainEntity save(SesionDomainEntity sesion);

    /**
     * Busca una sesión por su ID.
     * @param id El ID de la sesión.
     * @return Un Optional que contiene la sesión si se encuentra, o vacío si no.
     */
    Optional<SesionDomainEntity> findById(String id);

    /**
     * Devuelve todas las sesiones.
     * @return Una lista de todas las sesiones.
     */
    List<SesionDomainEntity> findAll();

    /**
     * Busca sesiones asociadas a una actividad específica.
     * @param actividadId El ID de la actividad.
     * @return Una lista de sesiones asociadas a la actividad.
     */
    List<SesionDomainEntity> findByActividadId(String actividadId);

    /**
     * Busca sesiones cuyo título contenga una cadena específica.
     * @param titulo La cadena a buscar en los títulos.
     * @return Una lista de sesiones cuyo título contiene la cadena.
     */
    List<SesionDomainEntity> findByTituloContaining(String titulo);

    /**
     * Busca sesiones dentro de un rango de fechas.
     * @param inicio La fecha de inicio del rango.
     * @param fin La fecha de fin del rango.
     * @return Una lista de sesiones dentro del rango de fechas.
     */
    List<SesionDomainEntity> findByFechaRange(LocalDateTime inicio, LocalDateTime fin);

    /**
     * Busca sesiones asociadas a un ponente específico.
     * @param ponente El nombre del ponente.
     * @return Una lista de sesiones asociadas al ponente.
     */
    List<SesionDomainEntity> findByPonente(String ponente);

    /**
     * Busca sesiones por modalidad.
     * @param modalidad La modalidad de las sesiones (PRESENCIAL, VIRTUAL, HIBRIDA).
     * @return Una lista de sesiones con la modalidad especificada.
     */
    List<SesionDomainEntity> findByModalidad(Modalidad modalidad);

    /**
     * Verifica si existe una sesión con el ID especificado.
     * @param id El ID de la sesión.
     * @return True si existe, false en caso contrario.
     */
    boolean existsById(String id);

    /**
     * Elimina una sesión por su ID.
     * @param id El ID de la sesión a eliminar.
     */
    void deleteById(String id);

    /**
     * Cuenta el número de sesiones asociadas a una actividad específica.
     * @param actividadId El ID de la actividad.
     * @return El número de sesiones asociadas a la actividad.
     */
    long countByActividadId(String actividadId);

    List<SesionDomainEntity> findByActividadIdAndModalidad(UUID fromString, Modalidad modalidad);

    public Optional<SesionDomainEntity> findByOrdenContainingAndActividad_Id(String orden , String actividadId);
}