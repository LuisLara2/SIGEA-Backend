package com.zentry.sigea.module_usuarios.infrastructure.database.entities;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "codigo_verificacion" , 
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"codigo" , "correo"} , 
            name = "Codigo_Correo_UQ"
        )
    }
)
@Getter
@Setter
public class CodigoVerificacionEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_codigo_verificacion" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "correo" , nullable = false , length = 100)
    private String correo;

    @Column(name = "codigo" , nullable = false , length = 255)
    private String codigo;

    @Column(name = "expires_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private Instant expiresAt = Instant.now().plus(5 , ChronoUnit.MINUTES);
}
