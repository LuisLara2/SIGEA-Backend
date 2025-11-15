package com.zentry.sigea.module_pago.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.zentry.sigea.module_pago.services.interfaces.IPago;

@Service
public class PagoService implements IPago {

    private static final Logger log = LoggerFactory.getLogger(PagoService.class);

    @Value("${mercadopago.access-token}")
    private String mercadoPagoAccessToken;



    @Override
    public Object pagarConYape(Object requestObj) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> request = (Map<String, Object>) requestObj;
            
            // 1. Configurar token de Mercado Pago
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            // 2. Crear cliente de pagos
            PaymentClient client = new PaymentClient();

            // 3. Construir request de pago
            PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                    // Información del pago
                    .description((String) request.get("descripcion"))
                    .transactionAmount(new BigDecimal(request.get("monto").toString()))
                    .installments(1) // Yape solo permite pagos al contado

                    // Método de pago
                    .paymentMethodId("yape")
                    .token((String) request.get("token")) // Token generado por el frontend

                    // Información del pagador
                    .payer(PaymentPayerRequest.builder()
                            .email((String) request.get("email"))
                            .build())
                    // Referencia externa (tu ID interno)
                    .externalReference((String) request.get("referencia"))
                    // Metadata adicional (opcional)
                    .metadata(createMetadata(request))
                    // Descripción en el estado de cuenta
                    .statementDescriptor("SIGEA")
                    .build();

            // 4. Crear el pago
            Payment payment = client.create(createRequest);

            // 5. Retornar respuesta exitosa
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("paymentId", payment.getId());
            response.put("status", payment.getStatus());
            response.put("statusDetail", payment.getStatusDetail());
            response.put("transactionAmount", payment.getTransactionAmount());
            response.put("dateCreated", payment.getDateCreated());
            response.put("mensaje", "Pago procesado correctamente");
            return response;

        } catch (MPApiException e) {
            // Error de la API de Mercado Pago
            log.error("Error API Mercado Pago: {}", e.getApiResponse().getContent(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "API_ERROR");
            errorResponse.put("mensaje", "Error al procesar el pago: " + e.getMessage());
            errorResponse.put("statusCode", e.getStatusCode());
            return errorResponse;

        } catch (MPException e) {
            // Error general de Mercado Pago
            log.error("Error Mercado Pago: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "MP_ERROR");
            errorResponse.put("mensaje", "Error en Mercado Pago: " + e.getMessage());
            return errorResponse;

        } catch (Exception e) {
            // Error inesperado
            log.error("Error inesperado: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "INTERNAL_ERROR");
            errorResponse.put("mensaje", "Error interno del servidor");
            return errorResponse;
        }   
    }

    @Override
    public Object consultarPago(Object requestObj) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> request = (Map<String, Object>) requestObj;
            
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            PaymentClient client = new PaymentClient();
            Payment payment = client.get(Long.valueOf(request.get("paymentId").toString()));

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("paymentId", payment.getId());
            response.put("status", payment.getStatus());
            response.put("statusDetail", payment.getStatusDetail());
            response.put("transactionAmount", payment.getTransactionAmount());
            response.put("dateCreated", payment.getDateCreated());
            return response;

        } catch (Exception e) {
            log.error("Error al consultar pago: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("mensaje", "Error al consultar el pago");
            return errorResponse;
        }
    }

    @Override
    public Object testEndpoint() {
        try {
            // Configurar token de Mercado Pago
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            // Crear cliente de pagos
            PaymentClient client = new PaymentClient();

            // Construir request de pago con datos hardcodeados para prueba
            PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                    .description("Titulo del producto")
                    .installments(1)
                    .payer(PaymentPayerRequest.builder()
                            .email("test_user_123@testuser.com")
                            .build())
                    .paymentMethodId("yape")
                    .token("ff8080814c11e237014c1ff593b57b4d")
                    .transactionAmount(new BigDecimal("5000"))
                    .build();

            // Crear el pago de prueba
            Payment payment = client.create(createRequest);

            // Retornar respuesta exitosa
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("paymentId", payment.getId());
            response.put("status", payment.getStatus());
            response.put("statusDetail", payment.getStatusDetail());
            response.put("transactionAmount", payment.getTransactionAmount());
            response.put("dateCreated", payment.getDateCreated());
            response.put("mensaje", "Pago de prueba procesado correctamente");
            response.put("testData", Map.of(
                "descripcion", "Titulo del producto",
                "email", "test_user_123@testuser.com",
                "monto", "5000"
            ));
            return response;

        } catch (MPApiException e) {
            // Error de la API de Mercado Pago
            log.error("Error API Mercado Pago en test: {}", e.getApiResponse().getContent(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "API_ERROR");
            errorResponse.put("mensaje", "Error al procesar el pago de prueba: " + e.getMessage());
            errorResponse.put("statusCode", e.getStatusCode());
            return errorResponse;

        } catch (MPException e) {
            // Error general de Mercado Pago
            log.error("Error Mercado Pago en test: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "MP_ERROR");
            errorResponse.put("mensaje", "Error en Mercado Pago: " + e.getMessage());
            return errorResponse;

        } catch (Exception e) {
            // Error inesperado
            log.error("Error inesperado en test: {}", e.getMessage(), e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "INTERNAL_ERROR");
            errorResponse.put("mensaje", "Error interno del servidor");
            return errorResponse;
        }
    }

    @Override
    public Map<String, Object> createMetadata(Map<String, Object> request) {
         Map<String, Object> metadata = new HashMap<>();
        metadata.put("sistema", "SIGEA");
        metadata.put("referencia", request.get("referencia"));
        if (request.get("usuarioId") != null) {
            metadata.put("usuario_id", request.get("usuarioId"));
        }
        return metadata;
    }




    
}