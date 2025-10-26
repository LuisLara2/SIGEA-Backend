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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "asistencia" , 
    indexes = {
        @Index(name = "idx_asistencia" , columnList = "sesion_id,inscripcion_id" , unique = true)
    }
)
@Getter
@Setter
@AllArgsConstructor
public class AsistenciaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asistencia")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sesion_id")
    private SesionEntity sesion;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscripcion_id")
    private InscripcionEntity inscripcion;

    @NotNull
    private Boolean presente = false;

    @NotNull
    @Column(name = "registrado_en" , columnDefinition = "TIMESTAMP")
    private LocalDateTime registradoEn = LocalDateTime.now();
}
