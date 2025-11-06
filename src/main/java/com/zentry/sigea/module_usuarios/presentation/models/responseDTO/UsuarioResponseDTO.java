package com.zentry.sigea.module_usuarios.presentation.models.responseDTO;

public class UsuarioResponseDTO {
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefonoCompleto;

    // Otros campos para la vista
    private String nombreRol;
    private String createdAt;
    private String updatedAt;

    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefonoCompleto() {
        return telefonoCompleto;
    }
    public void setTelefonoCompleto(String telefonoCompleto) {
        this.telefonoCompleto = telefonoCompleto;
    }

    public String getNombreRol() {
        return nombreRol;
    }
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
