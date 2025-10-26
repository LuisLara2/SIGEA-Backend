package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "metodo_pago"
)
@Getter
@Setter
@AllArgsConstructor
public class MetodoPagoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Long id;

    @NotNull(message = "El nombre del método no puede estar vacio.")
    @Size(max = 50 , message = "El nombre del método no debe tener más de 50 caracteres.")
    @Column(name = "nombre_modelo")
    private String nombreModelo;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @NotNull
    private Boolean activo = true;

    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
