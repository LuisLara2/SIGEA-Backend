package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
    name = "validacion" , 
    indexes = {
        @Index(name = "idx_validacion" , columnList = "certificado_id,tipo_validador_id" , unique = true)
    }
)
@Getter
@Setter
@AllArgsConstructor
public class ValidacionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validacion")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificado_id")
    private CertificadoEntity certificado;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_validador_id")
    private TipoValidadorEntity tipoValidador;

    @NotNull
    @Column(name = "fecha_validacion" , columnDefinition = "DATE")
    private LocalDate fechaValidacion = LocalDate.now();

    @NotNull
    @Size(max = 20 , message = "El resultado no puede tener más de 20 caracteres.")
    private String resultado = "APROBADO";

    @Lob
    @Column(columnDefinition = "TEXT")
    private String detalle;
}
