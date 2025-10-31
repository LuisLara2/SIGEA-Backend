package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "certificado"
)
@Getter
@Setter
public class CertificadoEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_certificado" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asistencia_id" , nullable = false)
    private AsistenciaEntity asistencia;

    @Column(name = "codigo_validacion" , nullable = false , length = 50)
    private String codigoValidacion;

    @Column(name = "fecha_emision" , nullable = false , columnDefinition = "DATE")
    private LocalDate fechaEmision = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_certificado_id" , nullable = false)
    private EstadoCertificadoEntity estadoCertificado;

    @Column(name = "url_pdf" , nullable = true , length = 300)
    private String urlPdf;

    @Column(name = "created_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at" , nullable = true , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
