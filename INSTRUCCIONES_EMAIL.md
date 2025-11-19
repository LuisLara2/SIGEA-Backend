# 📧 CONFIGURACIÓN DE EMAIL - INSTRUCCIONES

## ⚠️ IMPORTANTE: Completa estos pasos

### 1. Obtener contraseña de aplicación de Gmail

1. Ve a: https://myaccount.google.com/security
2. Activa "Verificación en 2 pasos" (si no está activada)
3. Busca "Contraseñas de aplicaciones"
4. Genera una nueva contraseña para "Correo"
5. **Copia la contraseña de 16 caracteres**

### 2. Configurar en application.properties

Abre `src/main/resources/application.properties` y busca la línea:

```
spring.mail.password=TU_CONTRASEÑA_DE_APLICACION_AQUI
```

**Reemplázala con tu contraseña de aplicación:**

```
spring.mail.password=xxxx xxxx xxxx xxxx
```

### 3. Reiniciar la aplicación

```bash
./mvnw clean compile
./mvnw spring-boot:run
```

## ✅ Resultado

Ahora **TODAS las notificaciones** se enviarán automáticamente por:

- ✅ **SISTEMA** (Base de datos) - SIEMPRE funciona
- ✅ **EMAIL** (Gmail) - Se intenta siempre, si falla no bloquea SISTEMA

### Notificaciones que envían email:

1. **Inscripciones** → "Inscripción confirmada: Te has inscrito exitosamente..."
2. **Actividades** → "Nueva actividad: [título] - [descripción]"
3. **Sesiones** → "Nueva sesión programada: [título] - Fecha: [fecha]"

## 🔒 Seguridad

⚠️ **NO SUBAS** `application.properties` con tu contraseña a GitHub

El archivo ya está en `.gitignore` para protegerlo.

## 🧪 Probar el sistema

1. Crear una inscripción en Postman
2. Verificar:
   - Logs: "SISTEMA: OK | EMAIL: OK"
   - Base de datos: Notificación guardada
   - Gmail: Email recibido en tu bandeja

---

**¿Problemas?**

Si ves "EMAIL: FALLÓ":
- Verifica tu contraseña de aplicación
- Revisa que la verificación en 2 pasos esté activa
- Checa los logs del servidor para más detalles
