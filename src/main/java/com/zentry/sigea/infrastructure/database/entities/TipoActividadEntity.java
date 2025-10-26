package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "tipo_actividad"
)
@Getter
@Setter
@AllArgsConstructor
public class TipoActividadEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_actividad")
    private Long id;

    @NotNull(message = "El nombre para la actividad no puede estar vacio.")
    @Size(max = 50 , message = "El nombre para la actividad no debe tener más de 50 caracteres")
    @Column(name = "nombre_actividad")
    private String nombreActividad;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull
    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @NotNull
    @Column(name = "updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
