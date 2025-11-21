-- Se va a cargar antes de insertar datos en data.sql

CREATE EXTENSION IF NOT EXISTS pgcrypto;
ALTER TABLE rol ALTER COLUMN id_rol SET DEFAULT gen_random_uuid();