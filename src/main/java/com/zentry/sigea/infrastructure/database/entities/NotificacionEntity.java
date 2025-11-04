package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;
import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "notificacion" , 
    indexes = {
        @Index(name = "idx_notificacion" , columnList = "usuario_id,fecha_envio" , unique = true)
    }
)
@Getter
@Setter
public class NotificacionEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_notificacion" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id" , nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id" , nullable = true)
    private ActividadEntity actividad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_notificacion_id" , nullable = false)
    private TipoNotificacionEntity tipoNotificacion;

    @Lob
    @Column(name = "mensaje" , nullable = false , columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_envio" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_notificacion_id" , nullable = false)
    private EstadoNotificacionEntity estadoNotificacion;
}
