package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "rol"
)
@Getter
@Setter
@AllArgsConstructor
public class RolEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @NotNull
    @Size(max = 50 , message = "El nombre del rol no puede tener más de 50 caracteres.")
    @Column(name = "nombre_rol")
    private String nombreRol;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @NotNull
    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @NotNull
    @Column(name = "updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
