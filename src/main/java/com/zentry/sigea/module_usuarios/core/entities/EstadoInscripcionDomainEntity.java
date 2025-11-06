package com.zentry.sigea.module_usuarios.core.entities;

public class EstadoInscripcionDomainEntity {
    private String codigo;
    private String etiqueta;

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
