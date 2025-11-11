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
    name = "usuario"
)
@Getter
@Setter
public class UsuarioEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_usuario" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "nombres" , nullable = false , length = 100)
    private String nombres;

    @Column(name = "apellidos" , nullable = false , length = 100)
    private String apellidos;
    
    @Column(name = "correo" , nullable = false , length = 100 , unique = true)
    private String correo;
    
    @Column(name = "password_hash" , nullable = false , length = 255)
    private String passwordHash;

    @Column(name = "created_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "telefono" , nullable = false , length = 25)
    private String telefono;

    @Column(name = "extension_telefonica" , nullable = false , length = 8)
    private String extensionTelefonica;
}
