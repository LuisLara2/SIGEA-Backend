package com.zentry.sigea.module_actividad.presentation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    
    @GetMapping(value = "/")
    public String Index(){
        return "hello";
    }
}
