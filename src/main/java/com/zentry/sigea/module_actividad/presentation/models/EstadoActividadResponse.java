package com.zentry.sigea.module_actividad.presentation.models;

import com.zentry.sigea.module_actividad.core.entities.actividad.EstadoActividad;

public class EstadoActividadResponse {
    private Long id;
    private String codigo;
    private String etiqueta;

    public EstadoActividadResponse() {
    }

    public EstadoActividadResponse(Long id, String codigo, String etiqueta) {
        this.id = id;
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }

    public Long getId() {
        return id;
    }


    public static EstadoActividadResponse fromEntity(EstadoActividad estadoActividad) {
        return new EstadoActividadResponse(
            estadoActividad.getId(),
            estadoActividad.getCodigo(),
            estadoActividad.getEtiqueta()
        );
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
    
}
