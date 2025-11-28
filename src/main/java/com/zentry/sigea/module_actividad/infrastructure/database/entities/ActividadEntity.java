package com.zentry.sigea.module_actividad.infrastructure.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import com.zentry.sigea.module_usuarios.infrastructure.database.entities.UsuarioEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "actividad"
)
@Getter
@Setter
public class ActividadEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_actividad" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @Column(name = "titulo" , nullable = false , length = 150)
    private String titulo;

    @Lob
    @Column(name = "descripcion" , columnDefinition = "TEXT" , nullable = true)
    private String descripcion;

    @Column(name = "fecha_inicio" , nullable = false , columnDefinition = "DATE")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin" , nullable = false , columnDefinition = "DATE")
    private LocalDate fechaFin;

    @Column(name = "hora_inicio" , nullable = true , columnDefinition = "TIME")
    private LocalTime horaInicio;

    @Column(name = "hora_fin" , nullable = true , columnDefinition = "TIME")
    private LocalTime horaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_actividad_id" , nullable = false)
    private EstadoActividadEntity estadoActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario" , nullable = false)
    private UsuarioEntity organizador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_actividad_id" , nullable = false)
    private TipoActividadEntity tipoActividad;

    @Column(name = "lugar" , nullable = true , length = 150)
    private String lugar;

    @Column(name = "banner_url" , nullable = true , length = 500)
    private String bannerUrl;

    @Column(name = "numero_yape" , nullable = true , length = 15)
    private String numeroYape;

    @Column(name = "created_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at" , nullable = true , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}