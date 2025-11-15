package com.zentry.sigea.module_pago.infrastucture.database.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidad JPA para la tabla pago según el esquema de base de datos
 */
@Entity
@Table(name = "pago")
@Getter
@Setter
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @Column(name = "inscripcion_id", nullable = false)
    private String inscripcionId;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "moneda", nullable = false, length = 3)
    private String moneda = "PEN";

    @Column(name = "fecha_pago", nullable = false)
    private OffsetDateTime fechaPago;

    @Column(name = "comprobante", length = 120)
    private String comprobante;

    @Column(name = "metodo_id", nullable = false)
    private String metodoId;

    @Column(name = "estado_id", nullable = false)
    private String estadoId;

    @Column(name = "referencia_ext", length = 120)
    private String referenciaExt;


    @PrePersist
    protected void onCreate() {
        if (fechaPago == null) {
            fechaPago = OffsetDateTime.now();
        }
        if (moneda == null) {
            moneda = "PEN";
        }
    }
}
