-- Script SQL para módulo de certificaciones (SIGEA)
-- Datos iniciales para el funcionamiento del sistema

-- ========================================
-- Estados de Certificado
-- ========================================
INSERT INTO estado_certificado (codigo, etiqueta) VALUES 
('EMITIDO', 'Certificado Emitido'),
('REVOCADO', 'Certificado Revocado'),
('SUSPENDIDO', 'Certificado Suspendido')
ON CONFLICT (codigo) DO NOTHING;

-- ========================================
-- Tipos de Validador
-- ========================================
INSERT INTO tipo_validador (codigo, etiqueta) VALUES 
('QR', 'Validación por Código QR'),
('HASH', 'Validación por Hash Criptográfico'),
('URL_PUBLICA', 'Validación por URL Pública'),
('OCSP', 'Validación OCSP (Online Certificate Status Protocol)')
ON CONFLICT (codigo) DO NOTHING;

-- ========================================
-- Índices adicionales para performance
-- ========================================
CREATE INDEX IF NOT EXISTS idx_certificado_fecha_emision ON certificado(fecha_emision);
CREATE INDEX IF NOT EXISTS idx_certificado_estado ON certificado(estado_id);
CREATE INDEX IF NOT EXISTS idx_validacion_fecha ON validacion(fecha_validacion);
CREATE INDEX IF NOT EXISTS idx_validacion_resultado ON validacion(resultado);

-- ========================================
-- Comentarios en tablas
-- ========================================
COMMENT ON TABLE certificado IS 'Almacena los certificados emitidos para las inscripciones';
COMMENT ON TABLE validacion IS 'Registra las validaciones realizadas sobre los certificados';
COMMENT ON TABLE estado_certificado IS 'Catálogo de estados posibles para los certificados';
COMMENT ON TABLE tipo_validador IS 'Catálogo de tipos de validadores disponibles';

-- ========================================
-- Comentarios en columnas principales
-- ========================================
COMMENT ON COLUMN certificado.codigo_validacion IS 'Código único para validar el certificado públicamente';
COMMENT ON COLUMN certificado.fecha_emision IS 'Fecha en que se emitió el certificado';
COMMENT ON COLUMN certificado.url_pdf IS 'URL donde se encuentra el archivo PDF del certificado';

COMMENT ON COLUMN validacion.resultado IS 'Resultado de la validación: APROBADO o RECHAZADO';
COMMENT ON COLUMN validacion.detalle IS 'Detalles adicionales sobre la validación realizada';

-- ========================================
-- Consulta de verificación
-- ========================================
SELECT 'Estados de certificado inicializados:' as mensaje;
SELECT codigo, etiqueta FROM estado_certificado ORDER BY codigo;

SELECT 'Tipos de validador inicializados:' as mensaje;
SELECT codigo, etiqueta FROM tipo_validador ORDER BY codigo;