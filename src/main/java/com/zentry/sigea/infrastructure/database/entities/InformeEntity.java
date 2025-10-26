package com.zentry.sigea.infrastructure.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "informe"
)
@Getter
@Setter
@AllArgsConstructor
public class InformeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informe")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    private ActividadEntity activdad;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_informe_id")
    private TipoInformeEntity tipoInforme;

    @NotNull
    @Column(name = "fecha_subida" , columnDefinition = "DATE")
    private LocalDate fechaSubida = LocalDate.now();

    @Size(max = 300 , message = "El URL no debe tener más de 300 caracteres.")
    @Column(name = "archivo_url")
    private String archivoUrl;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @NotNull
    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Column(name = "updated_at" , columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
