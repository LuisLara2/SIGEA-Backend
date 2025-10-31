package com.zentry.sigea.module_actividad.presentation.models;

import com.zentry.sigea.module_actividad.core.entities.actividad.TipoActividad;

public class TipoActividadResponse {

    private Long id;
    private String nombreActividad;
    private String descripcion;
    

    public TipoActividadResponse() {
    }

    public TipoActividadResponse(Long id, String nombreActividad, String descripcion) {
        this.id = id;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
    }


    /**
     * Factory method para crear un TipoActividadResponse desde una entidad TipoActividad
     */

    public static TipoActividadResponse fromEntity(TipoActividad tipoActividad) {
        
        return new TipoActividadResponse(
            tipoActividad.getId(),
            tipoActividad.getNombreActividad(),
            tipoActividad.getDescripcion()
        );
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreActividad() {
        return nombreActividad;
    }

}
