package com.zentry.sigea.infrastructure.database.entities;

import java.math.BigDecimal;
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PagoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscripcion_id")
    private InscripcionEntity inscripcion;

    @NotNull(message = "El monto no puede estar vacio.")
    @Column(precision = 10 , scale = 2) // similar a numeric(10 , 2)
    private BigDecimal monto;

    @NotNull
    @Column(columnDefinition = "CHAR(3)")
    private String moneda = "PEN";

    @NotNull
    @Column(name = "fecha_pago" , columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaPago = LocalDateTime.now();

    @Size(max = 120 , message = "No se debe exceder los 120 caracteres para el comprobante.")
    private String comprobante;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pago_id")
    private MetodoPagoEntity metodoPago;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_pago_id")
    private EstadoPagoEntity estadoPago;

    @Size(max = 120 , message = "No se debe superar los 120 caracteres.")
    @Column(name = "referencia_ext")
    private String referenciaExt;
}
