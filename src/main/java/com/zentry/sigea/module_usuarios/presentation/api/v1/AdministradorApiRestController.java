package com.zentry.sigea.module_usuarios.presentation.api.v1;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios/admin")
@CrossOrigin(origins = "http://localhost:16000") // El frontend estara en 16000 en local
public class AdministradorApiRestController {
    
    

}
