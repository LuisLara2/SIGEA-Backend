# 📧 Ejemplos Visuales de Notificaciones - SIGEA

Este documento muestra ejemplos reales de cómo se verán las notificaciones que recibirán los usuarios en sus correos y en el sistema.

---

## 💳 NOTIFICACIONES DE PAGOS

### 1️⃣ Pago por Inscripción a Actividad

**Escenario**: Juan se inscribe al curso "Desarrollo Web con Spring Boot" pagando S/. 250.00

**📱 Notificación en Sistema (Base de Datos)**
```
Tipo: PAGO
Estado: NO_LEIDO
Fecha: 16/11/2025 09:30

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Inscripción a actividad
🎯 Actividad: Desarrollo Web con Spring Boot

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: S/. 250.00
💳 Método de pago: Tarjeta de Crédito Visa
📄 Nº Comprobante: COMP-2025-001523
📅 Fecha y hora: 16/11/2025 09:30
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.
```

**📧 Email que llega a: juan.perez@gmail.com**
```
De: SIGEA - Sistema de Gestion <sigea@nilver.store>
Para: Juan Pérez <juan.perez@gmail.com>
Asunto: SIGEA - Pago Recibido

┌─────────────────────────────────────────┐
│           🎓 SIGEA                      │
│   Sistema de Gestión de Eventos        │
│         Académicos                      │
└─────────────────────────────────────────┘

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Inscripción a actividad
🎯 Actividad: Desarrollo Web con Spring Boot

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: S/. 250.00
💳 Método de pago: Tarjeta de Crédito Visa
📄 Nº Comprobante: COMP-2025-001523
📅 Fecha y hora: 16/11/2025 09:30
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.

─────────────────────────────────────────
SIGEA - Sistema de Gestión
© 2025 Todos los derechos reservados
```

---

### 2️⃣ Pago por Sesión Individual

**Escenario**: María paga S/. 80.00 para asistir solo a la sesión "Microservicios con Spring Cloud"

**📱 Notificación en Sistema**
```
Tipo: PAGO
Estado: NO_LEIDO
Fecha: 16/11/2025 14:15

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Pago por sesión individual
🎯 Actividad: Arquitectura de Software Empresarial
📅 Sesión: Microservicios con Spring Cloud

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: S/. 80.00
💳 Método de pago: Yape
📄 Nº Comprobante: YAPE-963852741
📅 Fecha y hora: 16/11/2025 14:15
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.
```

**📧 Email que llega a: maria.garcia@hotmail.com**
```
De: SIGEA - Sistema de Gestion <sigea@nilver.store>
Para: María García <maria.garcia@hotmail.com>
Asunto: SIGEA - Pago Recibido

┌─────────────────────────────────────────┐
│           🎓 SIGEA                      │
│   Sistema de Gestión de Eventos        │
│         Académicos                      │
└─────────────────────────────────────────┘

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Pago por sesión individual
🎯 Actividad: Arquitectura de Software Empresarial
📅 Sesión: Microservicios con Spring Cloud

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: S/. 80.00
💳 Método de pago: Yape
📄 Nº Comprobante: YAPE-963852741
📅 Fecha y hora: 16/11/2025 14:15
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.

─────────────────────────────────────────
SIGEA - Sistema de Gestión
© 2025 Todos los derechos reservados
```

---

### 3️⃣ Pago por Certificado

**Escenario**: Carlos completa el curso y paga S/. 50.00 por su certificado digital

**📱 Notificación en Sistema - PRIMERA (Pago)**
```
Tipo: PAGO
Estado: NO_LEIDO
Fecha: 16/11/2025 18:45

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Emisión de certificado
🎯 Actividad: Python para Ciencia de Datos

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: S/. 50.00
💳 Método de pago: Transferencia Bancaria BCP
📄 Nº Comprobante: TRANS-BCP-789456123
📅 Fecha y hora: 16/11/2025 18:45
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.
```

**📧 Email PRIMERA - Confirmación de Pago**
```
De: SIGEA - Sistema de Gestion <sigea@nilver.store>
Para: Carlos López <carlos.lopez@outlook.com>
Asunto: SIGEA - Pago Recibido

┌─────────────────────────────────────────┐
│           🎓 SIGEA                      │
│   Sistema de Gestión de Eventos        │
│         Académicos                      │
└─────────────────────────────────────────┘

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Emisión de certificado
🎯 Actividad: Python para Ciencia de Datos

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: S/. 50.00
💳 Método de pago: Transferencia Bancaria BCP
📄 Nº Comprobante: TRANS-BCP-789456123
📅 Fecha y hora: 16/11/2025 18:45
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.

─────────────────────────────────────────
SIGEA - Sistema de Gestión
© 2025 Todos los derechos reservados
```

**⏱️ 2-3 minutos después...**

**📱 Notificación en Sistema - SEGUNDA (Certificado Listo)**
```
Tipo: CERTIFICADO
Estado: NO_LEIDO
Fecha: 16/11/2025 18:47

🎓 ¡Felicidades! Tu certificado está listo

══════════════════════════════════════════

🎯 Actividad: Python para Ciencia de Datos
📋 Estado: Certificado generado
📅 Fecha de emisión: 16/11/2025

🔐 Código de validación:
   K7M3P9Q2X5W8A1N4Y6T2

📥 Descarga tu certificado:
   https://sigea.nilver.store/certificados/download/cert-2025-1523

══════════════════════════════════════════

💡 Puedes validar tu certificado usando el código de validación.
📌 Guarda este código para futuras referencias.

¡Felicitaciones por tu logro! 🎉
```

**📧 Email SEGUNDA - Certificado Listo**
```
De: SIGEA - Sistema de Gestion <sigea@nilver.store>
Para: Carlos López <carlos.lopez@outlook.com>
Asunto: SIGEA - Tu Certificado esta Listo

┌─────────────────────────────────────────┐
│           🎓 SIGEA                      │
│   Sistema de Gestión de Eventos        │
│         Académicos                      │
└─────────────────────────────────────────┘

🎓 ¡Felicidades! Tu certificado está listo

══════════════════════════════════════════

🎯 Actividad: Python para Ciencia de Datos
📋 Estado: Certificado generado
📅 Fecha de emisión: 16/11/2025

🔐 Código de validación:
   K7M3P9Q2X5W8A1N4Y6T2

📥 Descarga tu certificado:
   https://sigea.nilver.store/certificados/download/cert-2025-1523

══════════════════════════════════════════

💡 Puedes validar tu certificado usando el código de validación.
📌 Guarda este código para futuras referencias.

¡Felicitaciones por tu logro! 🎉

─────────────────────────────────────────
SIGEA - Sistema de Gestión
© 2025 Todos los derechos reservados
```

---

### 4️⃣ Pago por Material del Curso

**Escenario**: Ana compra material físico del curso por S/. 35.00

**📱 Notificación en Sistema**
```
Tipo: PAGO
Estado: NO_LEIDO
Fecha: 16/11/2025 11:20

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Material del curso
🎯 Actividad: Diseño UX/UI Avanzado

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: S/. 35.00
💳 Método de pago: Plin
📄 Nº Comprobante: PLIN-456123789
📅 Fecha y hora: 16/11/2025 11:20
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.
```

---

### 5️⃣ Pago por Membresía Anual

**Escenario**: Roberto paga membresía premium por $120.00 USD

**📱 Notificación en Sistema**
```
Tipo: PAGO
Estado: NO_LEIDO
Fecha: 16/11/2025 16:00

💳 ¡Pago recibido exitosamente!

══════════════════════════════════════════

📋 Concepto: Membresía o suscripción
🎯 Actividad: Membresía Premium SIGEA 2025

📊 Detalles de la transacción:
──────────────────────────────────────────
💰 Monto pagado: $ 120.00
💳 Método de pago: PayPal
📄 Nº Comprobante: PAYPAL-7X8M9N1Q2W3E
📅 Fecha y hora: 16/11/2025 16:00
──────────────────────────────────────────

✅ Tu transacción ha sido procesada correctamente.
📧 Conserva este comprobante para futuras referencias.
```

---

## 🎓 NOTIFICACIONES DE CERTIFICADOS

### Ejemplo 1: Certificado Gratuito (Sin Pago Previo)

**Escenario**: Laura completa curso gratuito "Introducción a Git" y automáticamente recibe su certificado

**📱 Notificación en Sistema**
```
Tipo: CERTIFICADO
Estado: NO_LEIDO
Fecha: 16/11/2025 20:15

🎓 ¡Felicidades! Tu certificado está listo

══════════════════════════════════════════

🎯 Actividad: Introducción a Git y GitHub
📋 Estado: Certificado generado
📅 Fecha de emisión: 16/11/2025

🔐 Código de validación:
   A3B7C9D2E5F8G1H4J6K8

📥 Descarga tu certificado:
   https://sigea.nilver.store/certificados/download/cert-2025-1524

══════════════════════════════════════════

💡 Puedes validar tu certificado usando el código de validación.
📌 Guarda este código para futuras referencias.

¡Felicitaciones por tu logro! 🎉
```

**📧 Email**
```
De: SIGEA - Sistema de Gestion <sigea@nilver.store>
Para: Laura Martínez <laura.martinez@gmail.com>
Asunto: SIGEA - Tu Certificado esta Listo

┌─────────────────────────────────────────┐
│           🎓 SIGEA                      │
│   Sistema de Gestión de Eventos        │
│         Académicos                      │
└─────────────────────────────────────────┘

🎓 ¡Felicidades! Tu certificado está listo

══════════════════════════════════════════

🎯 Actividad: Introducción a Git y GitHub
📋 Estado: Certificado generado
📅 Fecha de emisión: 16/11/2025

🔐 Código de validación:
   A3B7C9D2E5F8G1H4J6K8

📥 Descarga tu certificado:
   https://sigea.nilver.store/certificados/download/cert-2025-1524

══════════════════════════════════════════

💡 Puedes validar tu certificado usando el código de validación.
📌 Guarda este código para futuras referencias.

¡Felicitaciones por tu logro! 🎉

─────────────────────────────────────────
SIGEA - Sistema de Gestión
© 2025 Todos los derechos reservados
```

---

### Ejemplo 2: Certificado en Estado "EMITIDO"

**Escenario**: El certificado de Pedro es oficialmente emitido por la institución

**📱 Notificación en Sistema**
```
Tipo: CERTIFICADO
Estado: NO_LEIDO
Fecha: 16/11/2025 10:30

🎓 ¡Felicidades! Tu certificado está listo

══════════════════════════════════════════

🎯 Actividad: Desarrollo de APIs RESTful
📋 Estado: Certificado emitido
📅 Fecha de emisión: 15/11/2025

🔐 Código de validación:
   M5N8P2Q7R3S9T4U1V6W0

📥 Descarga tu certificado:
   https://sigea.nilver.store/certificados/download/cert-2025-1520

══════════════════════════════════════════

💡 Puedes validar tu certificado usando el código de validación.
📌 Guarda este código para futuras referencias.

¡Felicitaciones por tu logro! 🎉
```

---

### Ejemplo 3: Certificado sin URL (PDF en proceso)

**Escenario**: El certificado se creó pero el PDF aún se está generando

**📱 Notificación en Sistema**
```
Tipo: CERTIFICADO
Estado: NO_LEIDO
Fecha: 16/11/2025 22:00

🎓 ¡Felicidades! Tu certificado está listo

══════════════════════════════════════════

🎯 Actividad: Machine Learning con Python
📋 Estado: Certificado generado
📅 Fecha de emisión: 16/11/2025

🔐 Código de validación:
   X9Y2Z5A8B1C4D7E0F3G6

📥 Tu certificado estará disponible para descarga pronto.

══════════════════════════════════════════

💡 Puedes validar tu certificado usando el código de validación.
📌 Guarda este código para futuras referencias.

¡Felicitaciones por tu logro! 🎉
```

---

## 📊 Resumen de Flujos Completos

### 🔄 Flujo 1: Inscripción con Pago

```
1. Usuario se inscribe pagando S/. 250
   ↓
2. PagoService publica PagoCompletadoEvent (INSCRIPCION)
   ↓
3. Usuario recibe: "💳 Pago recibido - Concepto: Inscripción a actividad"
   ↓
4. Usuario puede asistir a las sesiones
```

### 🔄 Flujo 2: Pago por Sesión Individual

```
1. Usuario paga S/. 80 por sesión específica
   ↓
2. PagoService publica PagoCompletadoEvent (SESION)
   ↓
3. Usuario recibe: "💳 Pago recibido - Concepto: Pago por sesión individual"
   ↓
4. Usuario puede asistir a esa sesión específica
```

### 🔄 Flujo 3: Certificado con Pago (Completo)

```
1. Usuario completa el curso (80%+ asistencia)
   ↓
2. Usuario paga S/. 50 por certificado
   ↓
3. PagoService publica PagoCompletadoEvent (CERTIFICADO)
   ↓
4. Usuario recibe: "💳 Pago recibido - Concepto: Emisión de certificado"
   ↓
5. Sistema genera certificado automáticamente
   ↓
6. CertificadoService publica CertificadoGeneradoEvent
   ↓
7. Usuario recibe: "🎓 Tu certificado está listo - Código: K7M3P9Q2..."
   ↓
8. Usuario descarga su certificado en PDF
```

### 🔄 Flujo 4: Certificado Gratuito (Sin Pago)

```
1. Usuario completa curso gratuito (80%+ asistencia)
   ↓
2. Sistema genera certificado automáticamente
   ↓
3. CertificadoService publica CertificadoGeneradoEvent
   ↓
4. Usuario recibe: "🎓 Tu certificado está listo - Código: A3B7C9D2..."
   ↓
5. Usuario descarga su certificado en PDF
```

---

## 🎨 Vista de Bandeja de Entrada del Usuario

### Ejemplo: Carlos después del flujo completo de certificado con pago

**Gmail de carlos.lopez@outlook.com**

```
┌─────────────────────────────────────────────────────────────┐
│  📥 Bandeja de entrada (2 nuevos)                           │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ⭐ SIGEA - Sistema de Gestion                      18:47   │
│     SIGEA - Tu Certificado esta Listo                       │
│     🎓 ¡Felicidades! Tu certificado está listo...           │
│                                                              │
│  ⭐ SIGEA - Sistema de Gestion                      18:45   │
│     SIGEA - Pago Recibido                                   │
│     💳 ¡Pago recibido exitosamente!...                      │
│                                                              │
│  📧 Otros correos...                                         │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

---

## 💡 Notas Finales

✅ **Canal Dual**: Todas las notificaciones se guardan en BD + se envían por email

✅ **Formato Profesional**: Emojis + líneas divisorias + información estructurada

✅ **Información Completa**: Cada notificación tiene todos los detalles necesarios

✅ **Códigos de Validación**: Únicos de 20 caracteres para cada certificado

✅ **Comprobantes**: Cada pago tiene número de comprobante único

✅ **Monedas**: Soporte para PEN (S/.), USD ($), EUR (€)

✅ **Estados**: Certificados pueden estar en GENERADO, EMITIDO, VALIDADO, ANULADO

---

**Fecha de Ejemplos**: 16 de noviembre de 2025  
**Sistema**: SIGEA - Sistema de Gestión de Eventos Académicos  
**Estado**: ✅ Sistema de Notificaciones 100% Operativo
