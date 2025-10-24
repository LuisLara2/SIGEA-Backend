package com.zentry.sigea.presentation.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("")
public class HomeController {
    
    @GetMapping(value = "/")
    public String Index(){
        return "hello";
    }
}
