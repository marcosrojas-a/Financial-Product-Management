<div align="center">

# 🏦 Financial Products Management System

### Technical Challenge

Sistema para la administración de clientes, productos financieros y transacciones bancarias.

Desarrollado con **Java + Spring Boot + Angular** siguiendo **Arquitectura Hexagonal**.

---

![Java](https://img.shields.io/badge/Java-21-red?logo=openjdk)

![Spring](https://img.shields.io/badge/SpringBoot-3.x-6DB33F?logo=springboot)

![Angular](https://img.shields.io/badge/Angular-20-red?logo=angular)

![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue?logo=postgresql)

![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker)

![JUnit](https://img.shields.io/badge/JUnit-5-green)

![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-orange)

</div>

---

# 📖 Overview

Este proyecto corresponde a una prueba técnica cuyo objetivo consiste en desarrollar un sistema para administrar clientes, productos financieros y movimientos bancarios aplicando buenas prácticas de ingeniería de software.

El proyecto fue desarrollado siguiendo un proceso completo de análisis, diseño e implementación, priorizando la separación de responsabilidades mediante Arquitectura Hexagonal, principios SOLID y Clean Code.

---

# 🚀 Características

✔ Gestión de clientes

✔ Gestión de cuentas de ahorro

✔ Gestión de cuentas corrientes

✔ Consignaciones

✔ Retiros

✔ Transferencias

✔ Consulta de estado de cuenta

✔ Validaciones de negocio

✔ Persistencia en Base de Datos

✔ API REST

✔ Arquitectura Hexagonal

✔ Docker

✔ Pruebas Unitarias

---

# 🏛 Arquitectura

```
Frontend (Angular)

↓

REST Controllers

↓

Application Layer

↓

Domain

↓

Ports

↓

Adapters

↓

PostgreSQL
```

---

# 📂 Estructura del Proyecto

```
financial-products

├── backend
│ ├── application
│ ├── domain
│ ├── infrastructure
│ ├── adapters
│ └── config
│
├── frontend
│ ├── app
│ ├── components
│ ├── services
│ └── shared
│
├── docker
│
└── README.md
```

---

# 📋 Reglas de Negocio

Entre las principales reglas implementadas se encuentran:

- Clientes mayores de edad.
- No eliminar clientes con productos asociados.
- Cuentas de ahorro sin saldo negativo.
- Cancelación únicamente con saldo cero.
- Transferencias únicamente entre cuentas existentes.
- Número de cuenta generado automáticamente.
- Integridad referencial garantizada.

---

# 🛠 Tecnologías

| Tecnología | Uso |
|------------|------|
| Java | Backend |
| Spring Boot | API REST |
| Angular | Frontend |
| PostgreSQL | Base de Datos |
| Docker | Contenedores |
| JUnit | Testing |
| Maven | Gestión de dependencias |

---

# 📌 Casos de Uso

- Registrar Cliente
- Actualizar Cliente
- Eliminar Cliente
- Crear Producto Financiero
- Cancelar Cuenta
- Registrar Consignación
- Registrar Retiro
- Registrar Transferencia
- Consultar Estado de Cuenta

---

# ▶ Ejecución

## Backend

```bash
mvn clean install
mvn spring-boot:run
```

## Frontend

```bash
npm install
ng serve
```

## Docker

```bash
docker compose up --build
```

---

# 🧪 Testing

```bash
mvn test
```

---

# 👨‍💻 Autor

**Marcos Rojas Alvarez**

Desarrollador Backend Java | Spring Boot
