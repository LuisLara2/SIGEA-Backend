package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.zentry.sigea.module_actividad.infrastructure.database.entities.ActividadEntity;

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
    name = "informe"
)
@Getter
@Setter
public class InformeEntity {
    
    @Id
    @GeneratedValue
    @Column(
        name = "id_informe" , updatable = false , nullable = false , 
        columnDefinition = "UUID DEFAULT gen_random_uuid()"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id" , nullable = false)
    private ActividadEntity activdad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_informe_id" , nullable = false)
    private TipoInformeEntity tipoInforme;

    @Column(name = "fecha_subida" , nullable = false , columnDefinition = "DATE")
    private LocalDate fechaSubida = LocalDate.now();

    @Column(name = "archivo_url" , nullable = true , length = 300)
    private String archivoUrl;

    @Lob
    @Column(name = "observaciones" , nullable = true , columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "created_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at" , nullable = false , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
