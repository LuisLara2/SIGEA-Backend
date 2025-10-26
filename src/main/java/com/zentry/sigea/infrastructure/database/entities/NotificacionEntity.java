package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class NotificacionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    private ActividadEntity actividad;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_notificacion_id")
    private TipoNotificacionEntity tipoNotificacion;

    @NotNull(message = "El mansaje no puede estar vacio.")
    @Lob
    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @NotNull
    @Column(name = "fecha_envio" , columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_notificacion_id")
    private EstadoNotificacionEntity estadoNotificacion;
}
