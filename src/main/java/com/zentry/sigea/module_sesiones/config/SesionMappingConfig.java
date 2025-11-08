package com.zentry.sigea.module_sesiones.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración del módulo Sesiones
 */
@Configuration
@ComponentScan(basePackages = {
    "com.zentry.sigea.modules.sesiones"
})
public class SesionMappingConfig {
    // Configuración específica del módulo si es necesaria
}