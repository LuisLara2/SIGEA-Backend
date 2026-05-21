package com.zentry.sigea.module_actividad.presentation.models.requestDTO;

public class EstadoActividadRequest {
    private String codigo;
    private String etiqueta;

    public EstadoActividadRequest() {
    }

    public EstadoActividadRequest(String codigo, String etiqueta) {
        this.codigo = codigo;
        this.etiqueta = etiqueta;
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
