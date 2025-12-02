package com.zentry.sigea.module_usuarios.core.entities;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsuarioDomainEntity {
    private String id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String passwordHash;
    private String dni;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private String telefono;
    private String extensionTelefonica;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

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

    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public Boolean getCorreoVerificado() {
        return correoVerificado;
    }
    public void setCorreoVerificado(Boolean correoVerificado) {
        this.correoVerificado = correoVerificado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getExtensionTelefonica() {
        return extensionTelefonica;
    }
    public void setExtensionTelefonica(String extensionTelefonica) {
        this.extensionTelefonica = extensionTelefonica;
    }

    private Long calcDaysFromLastUpdate(){
        // Ejepmlo de algo que dbe ir aqui
        return ChronoUnit.DAYS.between(this.createdAt , this.updatedAt);
    }

    private String getFullPhoneNumber(){
        // Devolver algo como +51 900 700 897
        return this.telefono + this.extensionTelefonica;
    }

    public static UsuarioDomainEntity create(
        String nombres,
        String apellidos,
        String correo,
        String passwordHash,
        String dni,
        Boolean correoVerificado,
        String telefono,
        String extensionTelefonica
    ) {
        LocalDateTime nowDateTime = LocalDateTime.now();

        UsuarioDomainEntity usuarioDomainEntity = new UsuarioDomainEntity();

        usuarioDomainEntity.setNombres(nombres);
        usuarioDomainEntity.setApellidos(apellidos);
        usuarioDomainEntity.setCorreo(correo); 
        usuarioDomainEntity.setPasswordHash(passwordHash);
        usuarioDomainEntity.setDni(dni);
        usuarioDomainEntity.setCorreoVerificado(correoVerificado != null ? correoVerificado : false);
        usuarioDomainEntity.setCreatedAt(nowDateTime);
        usuarioDomainEntity.setUpdatedAt(nowDateTime);
        usuarioDomainEntity.setTelefono(telefono);
        usuarioDomainEntity.setExtensionTelefonica(extensionTelefonica);

        return usuarioDomainEntity;
    }
}
