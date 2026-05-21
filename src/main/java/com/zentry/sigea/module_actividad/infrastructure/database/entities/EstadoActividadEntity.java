package com.zentry.sigea.module_actividad.infrastructure.database.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "estado_actividad"
)
@Getter
@Setter
public class EstadoActividadEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_estado_actividad" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "codigo" , nullable = false , length = 30 , unique = true)
    private String codigo;

    @Column(name = "etiqueta" , nullable = true , length = 60)
    private String etiqueta;
}
