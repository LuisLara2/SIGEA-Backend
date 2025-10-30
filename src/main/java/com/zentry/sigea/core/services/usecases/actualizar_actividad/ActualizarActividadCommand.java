package com.zentry.sigea.core.services.usecases.actualizar_actividad;

import java.time.LocalDate;

/**
 * Command para actualizar una actividad existente
 */
public class ActualizarActividadCommand {
    private final Long actividadId;
    private final String title;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String location;

    public ActualizarActividadCommand(Long actividadId, String title, String description, 
                                   LocalDate startDate, LocalDate endDate, String location) {
        this.actividadId = actividadId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    // Getters
    public Long getActividadId() {
        return actividadId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }
}