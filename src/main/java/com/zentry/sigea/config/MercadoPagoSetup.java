package com.zentry.sigea.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mercadopago.MercadoPagoConfig;

@Component
public class MercadoPagoSetup {
    
    @Value("${mercadopago.access-token}")
    private String accessToken;
    
    public void configurar() {
        MercadoPagoConfig.setAccessToken(accessToken);
        // Configurar para Perú si es necesario
        // Para PERU usa MPE
        System.setProperty("mercadopago.region", "MPE");
    }
}
