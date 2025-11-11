package com.zentry.sigea.module_actividad.presentation.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class HomeController {
    
    @GetMapping(value = "/")
    public String Index(){
        return "el backend esta corriendo-se";
    }
}
