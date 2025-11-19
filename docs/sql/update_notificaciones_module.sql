-- =========================================================
-- SCRIPT DE ACTUALIZACIÓN PARA MÓDULO DE NOTIFICACIONES
-- =========================================================

-- 1. Agregar columna 'canal' a la tabla notificacion
ALTER TABLE notificacion 
ADD COLUMN IF NOT EXISTS canal VARCHAR(20) NOT NULL DEFAULT 'SISTEMA';

-- Agregar constraint para validar los valores del canal
ALTER TABLE notificacion 
ADD CONSTRAINT chk_canal CHECK (canal IN ('SISTEMA', 'CORREO', 'WHATSAPP'));

-- 2. Agregar columnas de auditoría created_at y updated_at
ALTER TABLE notificacion 
ADD COLUMN IF NOT EXISTS created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP;

-- 3. Verificar/Insertar TIPOS DE NOTIFICACIÓN completos
-- (INSCRIPCION, ACTIVIDAD, CERTIFICADO, PAGO, COMUNICACION)

-- Verificar si existen y crearlos si no
INSERT INTO tipo_notificacion (codigo, etiqueta)
SELECT 'INSCRIPCION', 'Notificación de Inscripción'
WHERE NOT EXISTS (SELECT 1 FROM tipo_notificacion WHERE codigo = 'INSCRIPCION');

INSERT INTO tipo_notificacion (codigo, etiqueta)
SELECT 'ACTIVIDAD', 'Notificación de Actividad'
WHERE NOT EXISTS (SELECT 1 FROM tipo_notificacion WHERE codigo = 'ACTIVIDAD');

INSERT INTO tipo_notificacion (codigo, etiqueta)
SELECT 'CERTIFICADO', 'Notificación de Certificado'
WHERE NOT EXISTS (SELECT 1 FROM tipo_notificacion WHERE codigo = 'CERTIFICADO');

INSERT INTO tipo_notificacion (codigo, etiqueta)
SELECT 'PAGO', 'Notificación de Pago'
WHERE NOT EXISTS (SELECT 1 FROM tipo_notificacion WHERE codigo = 'PAGO');

INSERT INTO tipo_notificacion (codigo, etiqueta)
SELECT 'COMUNICACION', 'Comunicación General'
WHERE NOT EXISTS (SELECT 1 FROM tipo_notificacion WHERE codigo = 'COMUNICACION');

-- 4. Verificar/Insertar ESTADOS DE NOTIFICACIÓN completos
-- (PENDIENTE, ENVIADA, LEIDA, FALLIDA)

INSERT INTO estado_notificacion (codigo, etiqueta)
SELECT 'PENDIENTE', 'Pendiente de Envío'
WHERE NOT EXISTS (SELECT 1 FROM estado_notificacion WHERE codigo = 'PENDIENTE');

INSERT INTO estado_notificacion (codigo, etiqueta)
SELECT 'ENVIADA', 'Notificación Enviada'
WHERE NOT EXISTS (SELECT 1 FROM estado_notificacion WHERE codigo = 'ENVIADA');

INSERT INTO estado_notificacion (codigo, etiqueta)
SELECT 'LEIDA', 'Notificación Leída'
WHERE NOT EXISTS (SELECT 1 FROM estado_notificacion WHERE codigo = 'LEIDA');

INSERT INTO estado_notificacion (codigo, etiqueta)
SELECT 'FALLIDA', 'Envío Fallido'
WHERE NOT EXISTS (SELECT 1 FROM estado_notificacion WHERE codigo = 'FALLIDA');

-- 5. Consultar los datos insertados para verificación
SELECT '=== TIPOS DE NOTIFICACIÓN ===' AS seccion;
SELECT id_tipo_notificacion, codigo, etiqueta FROM tipo_notificacion ORDER BY codigo;

SELECT '=== ESTADOS DE NOTIFICACIÓN ===' AS seccion;
SELECT id_estado_notificacion, codigo, etiqueta FROM estado_notificacion ORDER BY codigo;

SELECT '=== ESTRUCTURA DE TABLA NOTIFICACION ===' AS seccion;
SELECT 
    column_name, 
    data_type, 
    character_maximum_length, 
    is_nullable, 
    column_default
FROM information_schema.columns 
WHERE table_name = 'notificacion' 
ORDER BY ordinal_position;

-- Script completado exitosamente
SELECT 'Script de actualización ejecutado correctamente' AS resultado;
