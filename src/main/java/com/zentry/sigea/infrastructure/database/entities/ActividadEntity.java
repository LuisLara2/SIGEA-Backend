package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "actividad"
)
@Getter
@Setter
@AllArgsConstructor
public class ActividadEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long id;

    @NotNull(message = "El titulo no debe estar vacio.")
    @Size(max = 150 , message = "El titulo no debe tener más de 150 caracteres.")
    private String titulo;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "La fecha de inicio no debe estar vacia.")
    @Column(name = "fecha_inicio" , columnDefinition = "DATE")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no debe estar vacia.")
    @Column(name = "fecha_fin" , columnDefinition = "DATE")
    private LocalDate fechaFin;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_actividad_id")
    private EstadoActividadEntity estadoActividad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizador_id")
    private UsuarioEntity organizador;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_actividad_id")
    private TipoActividadEntity tipoActividad;

    @Size(max = 150 , message = "El lugar no debe tener más de 150 caracteres.")
    private String lugar;

    @NotNull
    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
