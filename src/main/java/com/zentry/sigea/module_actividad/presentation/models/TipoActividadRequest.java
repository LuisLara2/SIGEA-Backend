package com.zentry.sigea.module_actividad.presentation.models;

public class TipoActividadRequest {

    private final String nombreActividad;
    private final String descripcion;

    public TipoActividadRequest(String nombreActividad, String descripcion) {
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
