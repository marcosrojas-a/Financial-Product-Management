-- src/main/resources/data.sql

-- Tipos de documento (RF-001)
INSERT INTO tipo_documento (codigo, nombre, activo) VALUES
                                                        ('CC', 'Cédula de Ciudadanía', true),
                                                        ('CE', 'Cédula de Extranjería', true),
                                                        ('PA', 'Pasaporte', true)
    ON CONFLICT (codigo) DO NOTHING;

-- Tipos de producto (RN-012, RN-013 — prefijos 53/33 ya codificados en ProductType.java)
INSERT INTO tipo_producto (codigo, nombre, activo) VALUES
                                                       ('AHORROS', 'Cuenta de Ahorros', true),
                                                       ('CORRIENTE', 'Cuenta Corriente', true)
    ON CONFLICT (codigo) DO NOTHING;

-- Estados de producto (RN-016 — los únicos 3 permitidos)
INSERT INTO estado_producto (codigo, nombre, activo) VALUES
                                                         ('ACTIVA', 'Activa', true),
                                                         ('INACTIVA', 'Inactiva', true),
                                                         ('CANCELADA', 'Cancelada', true)
    ON CONFLICT (codigo) DO NOTHING;

-- Tipos de movimiento (RN-019 — los únicos 3 permitidos)
INSERT INTO tipo_movimiento (codigo, nombre, activo) VALUES
                                                         ('CONSIGNACION', 'Consignación', true),
                                                         ('RETIRO', 'Retiro', true),
                                                         ('TRANSFERENCIA', 'Transferencia', true)
    ON CONFLICT (codigo) DO NOTHING;