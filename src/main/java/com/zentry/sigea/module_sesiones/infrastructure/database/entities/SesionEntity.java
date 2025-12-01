package com.zentry.sigea.module_sesiones.infrastructure.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "sesion", 
    uniqueConstraints = {
        @UniqueConstraint(
            name = "ux_sesion" , 
            columnNames = {"actividad_id" , "fecha_sesion"}
        )
    }
)
@Getter
@Setter
public class SesionEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_sesion", updatable = false, nullable = false, 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id", nullable = false)
    private ActividadEntity actividad;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_sesion", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaSesion; // Campo en camelCase

    @Column(name = "hora_inicio", nullable = false, columnDefinition = "TIME")
    private java.time.LocalTime horaInicio; // Campo en camelCase

    @Column(name = "hora_fin", nullable = false, columnDefinition = "TIME")
    private java.time.LocalTime horaFin; // Campo en camelCase

    @Column(name = "ponente", nullable = true, length = 150)
    private String ponente;

    @Column(name = "modalidad", nullable = true, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private Modalidad modalidad;
    public enum Modalidad {
        PRESENCIAL,
        VIRTUAL,
        HIBRIDA
    }
    @Column(name = "lugar_sesion", nullable = true, length = 150)
    private String lugarSesion; // Campo en camelCase

    @Column(name = "link_virtual", nullable = true, length = 255)
    private String linkVirtual; // Campo en camelCase

    @Column(name = "orden", nullable = true)
    private String orden;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}