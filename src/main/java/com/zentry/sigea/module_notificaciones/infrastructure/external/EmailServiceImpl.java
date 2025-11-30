package com.zentry.sigea.module_notificaciones.infrastructure.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zentry.sigea.module_notificaciones.core.entities.NotificacionDomainEntity;
import com.zentry.sigea.module_notificaciones.core.ports.IEmailService;

import jakarta.mail.internet.MimeMessage;

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

    @Value("${sigea.host.url:http://localhost:8080}") 
    private String hostBase;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean enviar(NotificacionDomainEntity notificacion, String destinatario, String nombreDestinatario) {
        logger.info("EmailService.enviar() INICIADO - emailEnabled: {}", emailEnabled);
        logger.info("Destinatario: {}, Nombre: {}", destinatario, nombreDestinatario);

        if (!emailEnabled) {
            logger.warn("Envío de emails DESHABILITADO en configuración. Email no enviado a: {}", destinatario);
            return false;
        }

        if (destinatario == null || destinatario.trim().isEmpty()) {
            logger.error("Destinatario vacío para notificación ID: {}", notificacion.getId());
            return false;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom, nombreRemitente);
            helper.setTo(destinatario);
            helper.setSubject(obtenerAsunto(notificacion));

            String html = construirContenidoHtml(
                notificacion.getMensaje(),
                nombreDestinatario,
                notificacion.getTipoNotificacion() != null ? notificacion.getTipoNotificacion().getEtiqueta() : "Notificación",
                notificacion.getFechaEnvio().toString()
            );

            helper.setText(html, true);

            mailSender.send(message);
            return true;

        } catch (Exception e) {
            logger.error("ERROR al enviar email: {}", e.getMessage());
            return false;
        }
    }

    private String obtenerAsunto(NotificacionDomainEntity notificacion) {
        if (notificacion.getTipoNotificacion() == null) return "SIGEA - Notificación";

        return switch (notificacion.getTipoNotificacion().getCodigo()) {
            case "INSCRIPCION" -> "SIGEA - Inscripción Confirmada";
            case "COMUNICACION" -> "SIGEA - Comunicación";
            case "PAGO" -> "SIGEA - Pago Recibido";
            case "CERTIFICADO" -> "SIGEA - Certificado Disponible";
            default -> "SIGEA - Notificación";
        };
    }

    /**
     * 🔥 TU HTML COMPLETO INTEGRADO AQUÍ
     */
    private String construirContenidoHtml(String mensaje, String nombreDestinatario, String tipo, String fecha) {

        // URLs de logos desde /uploads/banners/
        String logoUnas = hostBase + "/uploads/banners/logoUnas.webp";
        String logoExtension = hostBase + "/uploads/banners/LogoExtensionUnas.webp";

        return """
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <title>SIGEA - Notificación</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <style>
    body { margin:0; padding:0; background-color:#eef1f5; font-family:Arial,sans-serif; }
    table { border-collapse:collapse; }
    img { display:block; max-width:100%; border:0; }
    .email-wrapper { width:100%; padding:32px 0; background:#eef1f5; }
    .email-container { max-width:680px; background:#fff; margin:auto; border-radius:18px; overflow:hidden;
                       box-shadow:0 8px 24px rgba(15,23,42,0.18); }

    .header { padding:24px 32px; border-bottom:1px solid #e5e7eb; }
    .brand-bar { background:linear-gradient(90deg,#0b63f3,#2563eb); color:#fff; padding:22px 32px; }
    .brand-name { font-size:20px; font-weight:800; letter-spacing:0.08em; margin-bottom:6px; }
    .content { padding:30px 40px; color:#111827; }
    .greeting { font-size:16px; }
    .message-box { background:#f9fafb; border-left:4px solid #0b63f3; border-radius:14px; padding:20px;
                   font-size:14px; color:#1f2937; margin-bottom:16px; }
    .meta-info { font-size:12px; color:#6b7280; margin-top:10px; }
    .footer { padding:18px 32px 22px; background:#f9fafb; border-top:1px solid #e5e7eb;
              text-align:center; font-size:11px; color:#6b7280; }
  </style>
</head>
<body>

<div class="email-wrapper">
  <table width="100%">
    <tr>
      <td align="center">
        <table class="email-container">

          <!-- HEADER -->
          <tr>
            <td class="header">
              <table width="100%">
                <tr>
                  <td width="25%" align="left">
                    <img src="%s" width="86" alt="Logo FIIS" />
                  </td>

                  <td align="center">
                    <div style="font-size:15px; font-weight:800; color:#0f172a;">UNIVERSIDAD NACIONAL AGRARIA DE LA SELVA</div>
                    <div style="font-size:13px; font-weight:600; color:#1f2937;">Facultad de Ingeniería de Sistemas e Informática</div>
                    <div style="font-size:12px; color:#4b5563;">Departamento de Extensión</div>
                  </td>

                  <td width="25%" align="right">
                    <img src="%s" width="76" alt="Logo Extensión UNAS" />
                  </td>
                </tr>
              </table>
            </td>
          </tr>

          <!-- BARRA SIGEA -->
          <tr>
            <td class="brand-bar">
              <div class="brand-name">SIGEA</div>
              <p>Sistema Integral de Gestión de Eventos Académicos</p>
            </td>
          </tr>

          <!-- CONTENIDO -->
          <tr>
            <td class="content">

              <p class="greeting"><strong>Hola %s,</strong></p>

              <div class="message-box"><p>%s</p></div>

              <div class="meta-info">
                <span><strong>Tipo:</strong> %s</span>
                <span><strong>Fecha:</strong> %s</span>
              </div>

            </td>
          </tr>

          <!-- FOOTER -->
          <tr>
            <td class="footer">
              <p>Este es un mensaje automático generado por SIGEA.</p>
              <p>Por favor, no responder este correo.</p>
              <p class="copy">&copy; 2025 SIGEA - Universidad Nacional Agraria de la Selva.</p>
            </td>
          </tr>

        </table>
      </td>
    </tr>
  </table>
</div>

</body>
</html>
""".formatted(logoUnas, logoExtension, nombreDestinatario, mensaje, tipo, fecha);
    }

    private String construirContenidoTexto(NotificacionDomainEntity notificacion, String nombreDestinatario) {
        return "Hola " + nombreDestinatario + "\n\n" +
               notificacion.getMensaje() + "\n\n" +
               "Fecha: " + notificacion.getFechaEnvio() + "\n\n" +
               "---\nSIGEA - Sistema Integral de Gestión de Eventos Académicos";
    }

    public boolean enviarCodigoVerificacion(String destinatario, String nombreDestinatario, Integer codigo) {
        if (!emailEnabled) throw new RuntimeException("Emails deshabilitados.");

        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setFrom(emailFrom, nombreRemitente);
            helper.setTo(destinatario);

            String mensaje = "Tu código de verificación es: " + codigo;
            helper.setText(construirContenidoHtml(mensaje, nombreDestinatario, "Verificación", java.time.LocalDate.now().toString()), true);

            mailSender.send(msg);
            return true;

        } catch (Exception e) {
            throw new RuntimeException("Error enviando código: " + e.getMessage());
        }
    }
}
