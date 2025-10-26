package com.zentry.sigea.infrastructure.database.entities;

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

@Entity
@Table(
    name = "usuario_telefono" , 
    indexes = {
        @Index(name="idx_usuario_telefono" , columnList = "usuario_id,telefono" , unique = true)
    }
)
public class UsuarioTelefonoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_telefono")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuarioEntity;

    @NotNull(message = "El telefono no puede estar vacio")
    @Size(max = 20 , message = "El telefono no puede tener más de 20 caracteres")
    private String telefono;

    @Size(max = 20 , message = "El tipo no puede tener más de 20 caracteres.")
    private String tipo;
}
