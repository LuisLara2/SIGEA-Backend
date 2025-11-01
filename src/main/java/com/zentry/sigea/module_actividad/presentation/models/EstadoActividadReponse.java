package com.zentry.sigea.module_actividad.presentation.models;

public class EstadoActividadReponse {
    private Long id;
    private String codigo;
    private String etiqueta;

    public EstadoActividadReponse() {
    }

    public EstadoActividadReponse(Long id, String codigo, String etiqueta) {
        this.id = id;
        this.codigo = codigo;
        this.etiqueta = etiqueta;
    }

    public Long getId() {
        return id;
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
