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
    name = "usuario"
)
@Getter
@Setter
@AllArgsConstructor
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotNull(message = "El nombre no debe estar vacio.")
    @Size(max = 100 , message = "El nombre no debe tener más de 100 caracteres.")
    private String nombres;
    

    @NotNull(message = "El apellido no debe estar vacio.")
    @Size(max = 100 , message = "El apellido no debe tener más de 100 caracteres.")
    private String apellidos;
    
    @NotNull(message = "El correo no debe estar vacio.")
    @Size(max = 100 , message = "El correo no debe tener más de 100 caracteres.")
    private String correo;
    
    @NotNull(message = "El hash no debe estar vacio.")
    @Size(max = 255 , message = "El hash no debe tener más de 255 caracteres.")
    @Column(name = "password_hash")
    private String passwordHash;

    @NotNull
    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Column(name = "updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
