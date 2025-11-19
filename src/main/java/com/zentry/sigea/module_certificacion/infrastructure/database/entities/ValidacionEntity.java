package com.zentry.sigea.module_certificacion.infrastructure.database.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "validacion" , 
    uniqueConstraints = {
        @UniqueConstraint(
            name = "ux_validacion_tipo_por_cert" , 
            columnNames = {"certificado_id" , "tipo_validador_id"} 
        )
    }
)
@Getter
@Setter
public class ValidacionEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_validacion" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificado_id" , nullable = false)
    private CertificadoEntity certificado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_validador_id" , nullable = false)
    private TipoValidadorEntity tipoValidador;

    @Column(name = "fecha_validacion" , nullable = false , columnDefinition = "DATE")
    private LocalDate fechaValidacion = LocalDate.now();

    @Column(name = "resultado" , nullable = false , length = 20)
    private String resultado = "APROBADO";

    @Lob
    @Column(name = "detalle" , nullable = true , columnDefinition = "TEXT")
    private String detalle;
}
