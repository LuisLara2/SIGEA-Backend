package com.zentry.sigea.infrastructure.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "estado_inscripcion"
)
@Getter
@Setter
@AllArgsConstructor
public class EstadoInscripcionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_inscripcion")
    private Long id;

    @NotNull(message = "El código no puede estar vacio.")
    @Size(max = 30 , message = "El código no debe tener más de 30 caracteres.")
    private String codigo;

    @Size(max = 60 , message = "La etiqueta no debe tener más de 60 caracteres.")
    private String etiqueta;
}
