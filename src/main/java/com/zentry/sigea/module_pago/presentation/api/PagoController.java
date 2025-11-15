package com.zentry.sigea.module_pago.presentation.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.zentry.sigea.module_pago.presentation.model.requestDTO.ConsultPagoRequest;
import com.zentry.sigea.module_pago.presentation.model.requestDTO.YapePaymentRequest;
import com.zentry.sigea.module_pago.presentation.model.responseDTO.ErrorResponse;
import com.zentry.sigea.module_pago.presentation.model.responseDTO.YapePaymentResponse;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private static final Logger log = LoggerFactory.getLogger(PagoController.class);

    @Value("${mercadopago.access-token}")
    private String mercadoPagoAccessToken;

    /**
     * Crea un pago directo con Yape El flujo es: 1. Frontend obtiene el token
     * del usuario (OTP o QR) 2. Backend procesa el pago 3. Se retorna el
     * resultado inmediato
     */
    @PostMapping("/crear-pago-yape")
    public Object pagarConYape(@RequestBody YapePaymentRequest request) {

        try {
            // 1. Configurar token de Mercado Pago
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            // 2. Crear cliente de pagos
            PaymentClient client = new PaymentClient();

            // 3. Construir request de pago
            PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                    // Información del pago
                    .description(request.getDescripcion())
                    .transactionAmount(request.getMonto())
                    .installments(1) // Yape solo permite pagos al contado

                    // Método de pago
                    .paymentMethodId("yape")
                    .token(request.getToken()) // Token generado por el frontend

                    // Información del pagador
                    .payer(PaymentPayerRequest.builder()
                            .email(request.getEmail())
                            .build())
                    // Referencia externa (tu ID interno)
                    .externalReference(request.getReferencia())
                    // Metadata adicional (opcional)
                    .metadata(crearMetadata(request))
                    // Descripción en el estado de cuenta
                    .statementDescriptor("SIGEA")
                    .build();

            // 4. Crear el pago
            Payment payment = client.create(createRequest);

            // 5. Retornar respuesta exitosa
            return YapePaymentResponse.builder()
                    .success(true)
                    .paymentId(payment.getId())
                    .status(payment.getStatus())
                    .statusDetail(payment.getStatusDetail())
                    .transactionAmount(payment.getTransactionAmount())
                    .dateCreated(payment.getDateCreated())
                    .mensaje("Pago procesado correctamente")
                    .build();

        } catch (MPApiException e) {
            // Error de la API de Mercado Pago
            log.error("Error API Mercado Pago: {}", e.getApiResponse().getContent(), e);
            return ErrorResponse.builder()
                    .success(false)
                    .error("API_ERROR")
                    .mensaje("Error al procesar el pago: " + e.getMessage())
                    .statusCode(e.getStatusCode())
                    .build();

        } catch (MPException e) {
            // Error general de Mercado Pago
            log.error("Error Mercado Pago: {}", e.getMessage(), e);
            return ErrorResponse.builder()
                    .success(false)
                    .error("MP_ERROR")
                    .mensaje("Error en Mercado Pago: " + e.getMessage())
                    .build();

        } catch (Exception e) {
            // Error inesperado
            log.error("Error inesperado: {}", e.getMessage(), e);
            return ErrorResponse.builder()
                    .success(false)
                    .error("INTERNAL_ERROR")
                    .mensaje("Error interno del servidor")
                    .build();
        }
    }

    /**
     * Consulta el estado de un pago
     */
    @PostMapping("/consultar-pago")
    public Object consultarPago(@RequestBody ConsultPagoRequest request) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            PaymentClient client = new PaymentClient();
            Payment payment = client.get(request.getPaymentId());

            return YapePaymentResponse.builder()
                    .success(true)
                    .paymentId(payment.getId())
                    .status(payment.getStatus())
                    .statusDetail(payment.getStatusDetail())
                    .transactionAmount(payment.getTransactionAmount())
                    .dateCreated(payment.getDateCreated())
                    .build();

        } catch (Exception e) {
            log.error("Error al consultar pago: {}", e.getMessage(), e);
            return ErrorResponse.builder()
                    .success(false)
                    .mensaje("Error al consultar el pago")
                    .build();
        }
    }

    /**
     * Crea metadata para el pago
     */
    private Map<String, Object> crearMetadata(YapePaymentRequest request) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("sistema", "SIGEA");
        metadata.put("referencia", request.getReferencia());
        if (request.getUsuarioId() != null) {
            metadata.put("usuario_id", request.getUsuarioId());
        }
        return metadata;
    }
}
