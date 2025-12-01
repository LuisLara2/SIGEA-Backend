package com.zentry.sigea.module_notificaciones.infrastructure.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zentry.sigea.module_notificaciones.core.entities.NotificacionDomainEntity;
import com.zentry.sigea.module_notificaciones.core.ports.IEmailService;
import jakarta.mail.internet.MimeMessage;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements IEmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    @Value("${notificaciones.email.from:noreply@sigea.com}")
    private String emailFrom;

    @Value("${notificaciones.email.enabled:false}")
    private boolean emailEnabled;

    @Value("${notificaciones.email.nombre-remitente:SIGEA - Sistema de Gestión}")
    private String nombreRemitente;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean enviar(NotificacionDomainEntity notificacion, String destinatario, String nombreDestinatario) {

        if (!emailEnabled) {
            logger.warn("Emails deshabilitados — no enviado.");
            return false;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom, nombreRemitente);
            helper.setTo(destinatario);
            helper.setSubject(obtenerAsunto(notificacion));

            String fechaRaw = notificacion.getFechaEnvio() != null
                ? notificacion.getFechaEnvio().toString()
                : "";

            String html = construirContenidoHtml(
                notificacion.getMensaje(),
                nombreDestinatario,
                notificacion.getTipoNotificacion() != null ? notificacion.getTipoNotificacion().getEtiqueta() : "Notificación",
                fechaRaw
            );

            helper.setText(html, true);

            mailSender.send(message);
            return true;

        } catch (Exception e) {
            logger.error("Error enviando correo: {}", e.getMessage(), e);
            return false;
        }
    }

    private String obtenerAsunto(NotificacionDomainEntity notificacion) {
        if (notificacion.getTipoNotificacion() == null) 
            return "SIGEA - Notificación";

        return switch (notificacion.getTipoNotificacion().getCodigo()) {
            case "INSCRIPCION" -> "SIGEA - Inscripción Confirmada";
            case "COMUNICACION" -> "SIGEA - Comunicación";
            case "PAGO" -> "SIGEA - Pago Recibido";
            case "CERTIFICADO" -> "SIGEA - Certificado Disponible";
            default -> "SIGEA - Notificación";
        };
    }

    private String construirContenidoHtml(String mensaje, String nombreDestinatario, String tipo, String fecha) {
        String logoUnas = "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoUnas_xxwueu.webp";
        String logoExtension = "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoExtensionUnas_xo0srw.webp";
        String nombreFinal = (nombreDestinatario != null && !nombreDestinatario.trim().isEmpty()) ? nombreDestinatario : "Usuario";
        String fechaFormateada = "";
        try {
            if (fecha != null && !fecha.isEmpty()) {
                java.time.LocalDateTime fechaObj = java.time.LocalDateTime.parse(fecha);
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                fechaFormateada = fechaObj.format(formatter);
            }
        } catch (Exception e) {
            fechaFormateada = fecha != null ? fecha : "";
        }
        return String.format(
            EmailTemplates.notificacionHtml(),
            logoUnas,
            logoExtension,
            nombreFinal,
            mensaje != null ? mensaje : "",
            tipo != null ? tipo : "",
            fechaFormateada
        );
    }

    @Override
    public boolean enviarCodigoVerificacion(String destinatario, String nombreDestinatario, Integer codigoVerificacion) {
        if (!emailEnabled) {
            logger.warn("Emails deshabilitados — no enviado.");
            return false;
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom, nombreRemitente);
            helper.setTo(destinatario);
            helper.setSubject("SIGEA - Código de Verificación");

            String html = construirHtmlCodigoVerificacion(nombreDestinatario, codigoVerificacion);
            helper.setText(html, true);

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            logger.error("Error enviando código de verificación: {}", e.getMessage(), e);
            return false;
        }
    }

    private String construirHtmlCodigoVerificacion(String nombreDestinatario, Integer codigoVerificacion) {
        String logoUnas = "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoUnas_xxwueu.webp";
        String logoExtension = "https://res.cloudinary.com/ddqasvhfw/image/upload/v1764533099/LogoExtensionUnas_xo0srw.webp";
        String nombreFinal = (nombreDestinatario != null && !nombreDestinatario.trim().isEmpty()) ? nombreDestinatario : "Usuario";
        return String.format(
            EmailTemplates.codigoVerificacionHtml(),
            logoUnas,
            logoExtension,
            nombreFinal,
            String.format("%06d", codigoVerificacion != null ? codigoVerificacion : 0)
        );
    }
}
