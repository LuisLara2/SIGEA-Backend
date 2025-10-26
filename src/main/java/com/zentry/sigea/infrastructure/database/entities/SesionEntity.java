package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "sesion" , 
    indexes = {
        @Index(name = "idx_sesion" , columnList = "actividad_id,fecha_sesion" , unique = true)
    }
)
@Getter
@Setter
@AllArgsConstructor
public class SesionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    private ActividadEntity actividad;

    @NotNull(message = "La fecha de sesion no puede estar vacia.")
    @Column(name = "fecha_sesion" , columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaSesion;

    @Size(max = 150 , message = "El titulo no debe tener más de 150 caracteres.")
    private String titulo;
}
