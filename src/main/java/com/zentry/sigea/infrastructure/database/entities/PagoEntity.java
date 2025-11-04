package com.zentry.sigea.infrastructure.database.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
    name = "pago" , 
    indexes = {
        @Index(name = "idx_pago" , columnList = "inscripcion_id,comprobante" , unique = true)
    }
)
@Getter
@Setter
public class PagoEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_pago" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscripcion_id" , nullable = false)
    private InscripcionEntity inscripcion;

    @Column(name = "monto" , precision = 10 , scale = 2 , nullable = false) // similar a numeric(10 , 2)
    private BigDecimal monto;

    @Column(name = "moneda" , nullable = false , columnDefinition = "CHAR(3)")
    private String moneda = "PEN";

    @Column(name = "fecha_pago" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaPago = LocalDateTime.now();

    @Column(name = "comprobante" , nullable = true , length = 120)
    private String comprobante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pago_id" , nullable = false)
    private MetodoPagoEntity metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_pago_id" , nullable = false)
    private EstadoPagoEntity estadoPago;

    @Column(name = "referencia_ext" , nullable = true , length = 120)
    private String referenciaExt;
}
