
package com.zentry.sigea.module_actividad.core.entities.actividad;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Actividad {
    private final Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private EstadoActividad statusId;
    // este es un ID de referencia a otro agregado, pero mas adelante se puede cambiar a un objeto de valor si es necesario USUARIO organizerId;
    private Long organizerId;
    private TipoActividad typeId;
    private String location;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Actividad(Long id, String title, String description, 
                   LocalDate startDate, LocalDate endDate,
                   EstadoActividad statusId, Long organizerId, TipoActividad typeId,
                   String location, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusId = statusId;
        this.organizerId = organizerId;
        this.typeId = typeId;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Actividad create(String title, String description,
                                 LocalDate startDate, LocalDate endDate,
                                 EstadoActividad statusId, Long organizerId,
                                 TipoActividad typeId, String location) {
        
        LocalDateTime now = LocalDateTime.now();
        return new Actividad(null, title, description, startDate, endDate,
                          statusId, organizerId, typeId, location, now, now);
    }

    // Métodos de negocio
    public void updateInfo(String title, String description, 
                          LocalDate startDate, LocalDate endDate, String location) {
        
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeStatus(EstadoActividad newStatusId) {
        if (newStatusId == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        this.statusId = newStatusId;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public boolean isFinished() {
        return LocalDate.now().isAfter(endDate);
    }

    public boolean isPending() {
        return LocalDate.now().isBefore(startDate);
    }

    public long getDurationInDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public EstadoActividad getStatusId() { return statusId; }
    public Long getOrganizerId() { return organizerId; }
    public TipoActividad getTypeId() { return typeId; }
    public String getLocation() { return location; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}