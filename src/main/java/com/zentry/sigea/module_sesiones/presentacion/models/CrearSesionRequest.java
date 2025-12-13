package com.zentry.sigea.module_sesiones.presentacion.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zentry.sigea.module_sesiones.infrastructure.database.entities.SesionEntity.Modalidad;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * DTO para crear una nueva sesión
 */
@Getter
@Setter
public class CrearSesionRequest {

    @NotBlank(message = "El ID de actividad es obligatorio")
    private String actividadId;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 150, message = "El título debe tener entre 3 y 150 caracteres")
    private String titulo;

    private String descripcion;

    @NotNull(message = "La fecha de sesión es obligatoria")
    @JsonProperty("fecha_sesion")
    private LocalDateTime fechaSesion;

    @NotNull(message = "La hora de inicio es obligatoria")
    @JsonProperty("horaInicio") 
    @Schema(type = "string", format = "time", example = "10:00:00") 
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @JsonProperty("horaFin") 
    @Schema(type = "string", format = "time", example = "12:00:00") 
    @JsonFormat(pattern = "HH:mm:ss") 
    private LocalTime horaFin;

    private String ponente;

    @NotNull(message = "La modalidad es obligatoria")
    private Modalidad modalidad; 

    private String lugarSesion;

    private String linkVirtual;

    private String orden;

    // Constructors
    public CrearSesionRequest() {}

    public CrearSesionRequest(
        String actividadId, 
        String titulo, 
        String descripcion, 
        LocalDateTime fechaSesion, 
        LocalTime horaInicio, 
        LocalTime horaFin, 
        String ponente, 
        Modalidad modalidad, 
        String lugarSesion, 
        String linkVirtual, 
        String orden
    ) {
        this.actividadId = actividadId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaSesion = fechaSesion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.ponente = ponente;
        this.modalidad = modalidad;
        this.lugarSesion = lugarSesion;
        this.linkVirtual = linkVirtual;
        this.orden = orden;
    }
}