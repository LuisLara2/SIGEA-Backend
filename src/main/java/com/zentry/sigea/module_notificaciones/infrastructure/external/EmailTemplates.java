package com.zentry.sigea.module_notificaciones.infrastructure.external;

public class EmailTemplates {
      /**
       * Construye el mensaje HTML para notificación de sesión con todos los campos relevantes.
       */public static String notificacionHtml() {
        return """
        <!DOCTYPE html>
        <html lang=\"es\">
        <head>
          <meta charset=\"UTF-8\" />
          <title>SIGEA - Notificación</title>
          <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />
          <style>
            body { margin:0; padding:0; background-color:#eef1f5; font-family:Arial,sans-serif; }
            table { border-collapse:collapse; }
            img { display:block; max-width:100%%; border:0; }
            .email-wrapper { width:100%%; padding:32px 0; background:#eef1f5; }
            .email-container { max-width:680px; background:#fff; margin:auto; border-radius:18px; overflow:hidden;
                               box-shadow:0 8px 24px rgba(15,23,42,0.18); }
            .header { padding:24px 32px; border-bottom:1px solid #e5e7eb; }
            .brand-bar { background:#0b63f3; color:#fff; padding:22px 32px; }
            .brand-name { font-size:20px; font-weight:800; letter-spacing:0.08em; margin-bottom:6px; }
            .content { padding:30px 40px; color:#111827; }
            .greeting { font-size:16px; margin-bottom:12px; }
            .message-box { background:#f9fafb; border-left:4px solid #0b63f3; border-radius:14px; padding:20px;
                           font-size:15px; color:#1f2937; margin-bottom:20px; }
            .footer { padding:18px 32px 22px; background:#f9fafb; border-top:1px solid #aab8d5ff;
                      text-align:center; font-size:11px; color:#6b7280; }
          </style>
        </head>

        <body>
        <div class=\"email-wrapper\">
          <table width=\"100%%\">
            <tr>
              <td align=\"center\">
                <table class=\"email-container\">

                  <!-- Encabezado institucional -->
                  <tr>
                    <td class=\"header\">
                      <table width=\"100%%\">
                        <tr>
                          <td width=\"25%%\" align=\"left\">
                            <img src=\"%s\" width=\"86\" alt=\"Logo FIIS\" />
                          </td>
                          <td align=\"center\">
                            <div style=\"font-size:15px; font-weight:800; color:#0f172a;\">UNIVERSIDAD NACIONAL AGRARIA DE LA SELVA</div>
                            <div style=\"font-size:13px; font-weight:600; color:#1f2937;\">Facultad de Ingeniería en Informática y Sistemas</div>
                            <div style=\"font-size:12px; color:#4b5563;\">Departamento de Extensión</div>
                          </td>
                          <td width=\"25%%\" align=\"right\">
                            <img src=\"%s\" width=\"76\" alt=\"Logo Extensión UNAS\" />
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>

                  <!-- Barra azul -->
                  <tr>
                    <td class=\"brand-bar\">
                      <div class=\"brand-name\">SIGEA</div>
                      <p>Sistema Integral de Gestión de Eventos Académicos</p>
                    </td>
                  </tr>

                  <!-- Contenido -->
                  <tr>
                    <td class=\"content\">
                      <p class=\"greeting\"><strong>Hola %s,</strong></p>

                      <!-- MENSAJE PERSONALIZADO (ACTIVIDAD O SESIÓN) -->
                      <div class=\"message-box\">
                        %s
                      </div>

                    </td>
                  </tr>

                  <!-- Footer -->
                  <tr>
                    <td class=\"footer\">
                      <p style=\"margin:4px 0;\">Facultad de Ingeniería en Informática y Sistemas – Universidad Nacional Agraria de la Selva</p>
                      <p style=\"margin:4px 0;\">Carretera Central Km 1.21 – Tingo María, Perú</p>
                      <p style=\"margin:4px 0;\">© 2025 SIGEA · Todos los derechos reservados</p>
                    </td>
                  </tr>

                </table>
              </td>
            </tr>
          </table>
        </div>
        </body>
        </html>
        """;
    }
    public static String codigoVerificacionHtml() {
    return """
    <!DOCTYPE html>
    <html lang=\"es\">
    <head>
      <meta charset=\"UTF-8\" />
      <title>Verificación de Seguridad – SIGEA</title>
      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />
      <style>
        body {
          margin:0; padding:0;
          background-color:#f3f4f6;
          font-family: 'Arial', sans-serif;
        }
        img { display:block; max-width:100%%; }

        .wrapper {
          width:100%%; padding:40px 0;
          background:#f3f4f6;
        }

        .card {
          max-width:520px;
          background:#fff;
          margin:auto;
          border-radius:20px;
          padding:28px 50px 18px 50px; 
          box-shadow:0 4px 14px rgba(0,0,0,0.1);
          text-align:center;
        }

        .title {
          font-size:22px;
          font-weight:700;
          color:#111827;
          margin-top:10px;
          margin-bottom:6px;
        }

        .subtitle {
          font-size:14px;
          color:#6b7280;
          margin:4px 0;
        }

        .code-box {
          background:#eef2ff;
          border:2px solid #4f46e5;
          border-radius:12px;
          padding:18px;
          font-size:32px;
          font-weight:bold;
          color:#1e3a8a;
          letter-spacing:0.30em;
          margin:10px auto 6px auto;  
          width:70%%;
        }

        .text-valid {
          font-size:14px;
          color:#6b7280;
          margin:4px 0;
        }

        .footer {
          font-size:11px;
          color:#6b7280;
          text-align:center;
          line-height:1.2;
          background:#ececec;
          border-radius:0 0 20px 20px;
          padding:14px 0 10px 0;
          width:100%%;
          box-sizing:border-box;
          display:block;
        }

        .logos {
          margin-bottom:10px; 
          display:flex;
          justify-content:space-between;
          align-items:center;
        }
      </style>
    </head>

    <body>
    <div class=\"wrapper\">
      <div class=\"card\">

        <div class=\"logos\">
          <img src=\"%s\" width=\"80\" />
          <img src=\"%s\" width=\"70\" />
        </div>

        <div class=\"title\">Código de Verificación</div>
        <div class=\"subtitle\">Hola %s, usa este código para continuar</div>

        <div class=\"code-box\">%s</div>

        <div class=\"text-valid\">Este código es válido solo por unos minutos.</div>

        <div class=\"footer\">
          Facultad de Ingeniería en Informática y Sistemas – UNAS<br/>
          © 2025 SIGEA – Todos los derechos reservados
        </div>

      </div>
    </div>
    </body>
    </html>
    """;
}
}