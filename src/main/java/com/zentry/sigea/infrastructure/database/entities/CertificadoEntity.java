package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    name = "certificado"
)
@Getter
@Setter
@AllArgsConstructor
public class CertificadoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_certificado")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asistencia_id")
    private AsistenciaEntity asistencia;

    @NotNull(message = "El codigo de validacion no debe estar vacio.")
    @Size(max = 50 , message = "El codigo de validacion no debe tener mas de 50 caracteres.")
    @Column(name = "codigo_validacion")
    private String codigoValidacion;

    @NotNull
    @Column(name = "fecha_emision" , columnDefinition = "DATE")
    private LocalDate fechaEmision = LocalDate.now();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_certificado_id")
    private EstadoCertificadoEntity estadoCertificado;

    @Size(max = 300 , message = "La URL no debe tener mas de 300 caracteres.")
    @Column(name = "url_pdf")
    private String urlPdf;

    @NotNull
    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
