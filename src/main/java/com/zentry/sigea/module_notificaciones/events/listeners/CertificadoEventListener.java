package com.zentry.sigea.module_notificaciones.events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.zentry.sigea.module_notificaciones.events.domain.CertificadoGeneradoEvent;
import com.zentry.sigea.module_notificaciones.presentation.models.requestDTO.CrearNotificacionRequest;
import com.zentry.sigea.module_notificaciones.services.NotificacionService;

@Component
public class CertificadoEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CertificadoEventListener.class);
    private static final String TIPO_NOTIFICACION_CERTIFICADO = "CERTIFICADO";

    private final NotificacionService notificacionService;

    public CertificadoEventListener(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @EventListener
    @Async
    public void onCertificadoGenerado(CertificadoGeneradoEvent event) {
        try {
            logger.info(
                "Evento recibido: Certificado generado {} para usuario {} - Código: {}",
                event.getCertificadoId(),
                event.getUsuarioId(),
                event.getCodigoValidacion()
            );

            // Formatear fecha de emisión
            String fechaFormateada = event.getFechaEmision()
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // MENSAJE HTML PROFESIONAL Y COMPACTO
            String mensaje = """
🏅 <strong>¡Felicidades! Tu certificado ha sido emitido</strong><br>

<div style='font-size:14px; line-height:1.55;'>

<b>🎯 Actividad:</b> %s<br>
<b>📋 Estado:</b> %s<br>
<b>📅 Fecha de emisión:</b> %s<br>

<b>🔐 Código de validación:</b>

<div style='text-align:center; margin:10px 0 4px 0;'>
    <div style="
        background:#f0f4ff;
        border:2px solid #3b5ccc;
        border-radius:10px;
        padding:8px 14px;
        font-size:19px;
        font-weight:700;
        color:#1e3a8a;
        letter-spacing:0.12em;
        display:inline-block;
        min-width:170px;
    ">
        %s
    </div>
</div>

%s

📌 Puedes validar tu certificado usando el código de validación.<br>
📌 Guarda este código para futuras referencias.<br>

<br>¡Felicitaciones por tu logro! 🎉
</div>
""".formatted(
                    event.getActividadTitulo(),
                    event.getEstado().getDescripcion(),
                    fechaFormateada,
                    event.getCodigoValidacion(),
                    generarDescargaCompacta(event.getUrlPdf())
            );

            // Crear notificación
            CrearNotificacionRequest request = new CrearNotificacionRequest(
                event.getUsuarioId(),
                event.getActividadId(),
                TIPO_NOTIFICACION_CERTIFICADO,
                mensaje,
                null,
                "SISTEMA"
            );

            String resultado = notificacionService.crearNotificacion(request);

            logger.info(
                "Notificación de certificado enviada exitosamente - Usuario: {}, Código: {}",
                event.getUsuarioId(),
                event.getCodigoValidacion()
            );

        } catch (Exception e) {
            logger.error(
                "Error al procesar notificación de certificado para usuario {}: {}",
                event.getUsuarioId(), e.getMessage(), e
            );
        }
    }

    /**
     * Genera el bloque HTML compacto para descargar el certificado
     */
    private String generarDescargaCompacta(String urlPdf) {
        if (urlPdf == null || urlPdf.isEmpty()) {
            return """
📥 Tu certificado estará disponible para descargar pronto.<br><br>
""";
        }

        return """
📥 <b>Descargar certificado:</b>
<a href='%s' style='color:#2563eb; font-weight:600;'>Haz clic aquí para descargar</a><br><br>
""".formatted(urlPdf);
    }
}
