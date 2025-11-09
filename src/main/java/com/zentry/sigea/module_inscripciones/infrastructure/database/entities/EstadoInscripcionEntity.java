package com.zentry.sigea.module_inscripciones.infrastructure.database.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estado_inscripcion")
@Getter
@Setter
public class EstadoInscripcionEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_estado_inscripcion", 
        updatable = false, 
        nullable = false, 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(name = "etiqueta", nullable = true, length = 60)
    private String etiqueta;
}

