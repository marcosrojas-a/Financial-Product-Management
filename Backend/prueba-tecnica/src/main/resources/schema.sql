-- src/main/resources/schema.sql

-- Catálogos (sin dependencias)
CREATE TABLE IF NOT EXISTS tipo_documento (

    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS tipo_producto (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS estado_producto (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(30) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS tipo_movimiento (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
    );

-- Cliente (depende de tipo_documento)
CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY,
    tipo_documento_id BIGINT NOT NULL REFERENCES tipo_documento(id),
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    fecha_nacimiento DATE NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT now(),
    fecha_modificacion TIMESTAMP
    );

-- Producto financiero (depende de cliente, tipo_producto, estado_producto)
CREATE TABLE IF NOT EXISTS producto_financiero (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL REFERENCES cliente(id),
    tipo_producto_id BIGINT NOT NULL REFERENCES tipo_producto(id),
    estado_producto_id BIGINT NOT NULL REFERENCES estado_producto(id),
    numero_cuenta VARCHAR(10) NOT NULL UNIQUE,
    saldo DECIMAL(18,2) NOT NULL DEFAULT 0,
    saldo_disponible DECIMAL(18,2) NOT NULL DEFAULT 0,
    exenta_gmf BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT now(),
    fecha_modificacion TIMESTAMP
    );

-- Movimiento (depende de producto_financiero, tipo_movimiento)
CREATE TABLE IF NOT EXISTS movimiento (
    id BIGSERIAL PRIMARY KEY,
    producto_id BIGINT NOT NULL REFERENCES producto_financiero(id),
    tipo_movimiento_id BIGINT NOT NULL REFERENCES tipo_movimiento(id),
    valor DECIMAL(18,2) NOT NULL,
    saldo_anterior DECIMAL(18,2) NOT NULL,
    saldo_nuevo DECIMAL(18,2) NOT NULL,
    descripcion VARCHAR(255),
    fecha_movimiento TIMESTAMP NOT NULL DEFAULT now()
    );

-- Transferencia (depende de movimiento, dos veces)
CREATE TABLE IF NOT EXISTS transferencia (
    id BIGSERIAL PRIMARY KEY,
    movimiento_origen_id BIGINT NOT NULL REFERENCES movimiento(id),
    movimiento_destino_id BIGINT NOT NULL REFERENCES movimiento(id),
    fecha_transferencia TIMESTAMP NOT NULL DEFAULT now()
    );