package com.zentry.sigea.module_actividad.core.usecases.actividad;

import java.time.LocalDate;

import com.zentry.sigea.module_actividad.core.entities.actividad.Actividad;
import com.zentry.sigea.module_actividad.core.repositories.ActividadRepository;

/**
 * Caso de uso para actualizar una actividad existente
 * Aplica principios DDD manteniendo la lógica de negocio en la entidad
 */
public class ActualizarActividadUseCase {
    
    private final ActividadRepository actividadRepository;

    public ActualizarActividadUseCase(ActividadRepository actividadRepository) {
        this.actividadRepository = actividadRepository;
    }

    public Actividad execute(ActualizarActividadCommand command) {
        // Obtener la actividad existente
        Actividad actividad = actividadRepository.findById(command.getActividadId())
            .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));

        // Validaciones de negocio específicas
        validateBusinessRules(actividad, command);

        // Delegar la actualización a la entidad de dominio
        actividad.updateInfo(
            command.getTitle(),
            command.getDescription(),
            command.getStartDate(),
            command.getEndDate(),
            command.getLocation()
        );

        // Persistir los cambios
        return actividadRepository.save(actividad);
    }

    private void validateBusinessRules(Actividad actividad, ActualizarActividadCommand command) {
        // No permitir actualizar actividades que ya terminaron
        if (actividad.isFinished()) {
            throw new IllegalArgumentException("No se puede actualizar una actividad que ya terminó");
        }

        // Si la actividad ya comenzó, no permitir cambiar las fechas
        if (actividad.isActive() && 
            (!actividad.getStartDate().equals(command.getStartDate()) || 
             !actividad.getEndDate().equals(command.getEndDate()))) {
            throw new IllegalArgumentException("No se pueden cambiar las fechas de una actividad que ya comenzó");
        }

        // Validar que las nuevas fechas no sean en el pasado (si la actividad aún no comenzó)
        if (actividad.isPending() && command.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("No se puede programar una actividad en el pasado");
        }
    }
}