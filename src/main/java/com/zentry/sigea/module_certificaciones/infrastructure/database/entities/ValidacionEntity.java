package com.zentry.sigea.module_certificaciones.infrastructure.database.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "validacion",
       indexes = {
           @Index(name = "ux_validacion_tipo_por_cert", 
                  columnList = "certificado_id, tipo_validador_id", 
                  unique = true)
       })

@Getter
@Setter
public class ValidacionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validacion", columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID idValidacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificado_id", nullable = false)
    private CertificadoEntity certificado;
    
    @Column(name = "tipo_validador_id", nullable = false)
    private String tipoValidador;
    
    @Column(name = "fecha_validacion", nullable = false)
    private LocalDate fechaValidacion;
    
    @Column(name = "resultado", length = 20, nullable = false)
    private String resultado = "APROBADO"; // APROBADO | RECHAZADO
    
    @Column(name = "detalle", columnDefinition = "TEXT")
    private String detalle;
    @PrePersist
    protected void onCreate() {
        fechaValidacion = LocalDate.now();
    }
    
    

}