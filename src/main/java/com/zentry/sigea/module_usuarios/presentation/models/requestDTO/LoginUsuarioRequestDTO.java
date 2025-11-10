package com.zentry.sigea.module_usuarios.presentation.models.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginUsuarioRequestDTO {
    
    @NotNull(message = "Debe ingresar un correo.")
    @Email(message = "Debe escribir un correo valido.")
    @Size(max = 100 , message = "El correo no debe tener más de 100 caracteres.")

    private String correo;
    
    @NotNull(message = "Debe ingresar la contraseña.")
    @Size(min = 8 , max = 100 , message = "La contraseña debe tener entre 8 y 100 caracteres de longitud.")
    private String password;

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }
}
