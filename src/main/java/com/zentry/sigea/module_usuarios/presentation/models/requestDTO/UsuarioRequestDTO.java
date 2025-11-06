package com.zentry.sigea.module_usuarios.presentation.models.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {
    
    @NotNull(message = "Debe proporcionar sus nombres.")
    @Size(message = "Sus nombres no deben tener más de 100 caracteres.")
    private String nombres;
    
    @NotNull(message = "Debe proporcionar sus apellidos.")
    @Size(message = "Sus apellidos no deben tener más de 100 caracteres.")
    private String apellidos;

    @NotNull(message = "Debe proporcionar un correo.")
    @Email(message = "Debe escribir un correo valido.")
    @Size(max = 100 , message = "El correo no debe tener más de 100 caracteres.")
    private String correo;
    
    @NotNull(message = "Debe colocar una contraseña.")
    @Size(min = 8 , max = 100 , message = "La contraseña debe tener entre 8 y 100 caracteres de longitud.")
    private String password;

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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
