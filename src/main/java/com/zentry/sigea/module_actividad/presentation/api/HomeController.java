package com.zentry.sigea.module_actividad.presentation.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("")
@Tag(name = "Home", description = "API de la ruta principal")
@CrossOrigin(origins = "*")
public class HomeController {
    
    @GetMapping(value = "/")
    public String Index(){
        return "el backend esta corriendo-se";
    }
}
