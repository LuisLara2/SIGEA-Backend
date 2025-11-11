package com.zentry.sigea.module_usuarios.infrastructure.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "rol"
)
@Getter
@Setter
public class RolEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_rol" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "nombre_rol" , nullable = false , length = 50 , unique = true)
    private String nombreRol;

    @Column(name = "descripcion" , nullable = true , columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "created_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
