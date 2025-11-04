package com.zentry.sigea.infrastructure.database.entities;

import java.util.UUID;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

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
    name = "usuario_telefono" , 
    indexes = {
        @Index(name="idx_usuario_telefono" , columnList = "usuario_id,telefono" , unique = true)
    }
)
@Getter
@Setter
public class UsuarioTelefonoEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_usuario_telefono" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id" , nullable = false)
    private UsuarioEntity usuarioEntity;

    @Column(name = "telefono" , nullable = false , length = 20)
    private String telefono;

    @Column(name = "tipo" , nullable = true , length = 20)
    private String tipo;
}
