package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "inscripcion" , 
    indexes = {
        @Index(name = "idx_inscripcion" , columnList = "usuario_id,actividad_id" , unique = true)
    }
)
@Getter
@Setter
public class InscripcionEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_inscripcion" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "fecha_inscripcion" , nullable = false , columnDefinition = "DATE")
    private LocalDate fechaInscripcion = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id" , nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id" , nullable = false)
    private ActividadEntity actividad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_inscripcion_id" , nullable = false)
    private EstadoInscripcionEntity estadoInscripcion;
}
