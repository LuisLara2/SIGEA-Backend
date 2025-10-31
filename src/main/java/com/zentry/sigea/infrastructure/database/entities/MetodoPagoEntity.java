package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "metodo_pago"
)
@Getter
@Setter
public class MetodoPagoEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_metodo_pago" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "nombre_modelo" , nullable = false , length = 50)
    private String nombreModelo;
    
    @Lob
    @Column(name = "descripcion" , nullable = true , columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(name = "activo" , nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at" , nullable = true , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at" , nullable = true , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
