<div align="center">

# 🏦 Financial Products Management System

### Technical Challenge

Sistema para la administración de clientes, productos financieros y transacciones bancarias.

Desarrollado con **Java + Spring Boot + Angular** siguiendo **Arquitectura Hexagonal**.

---

![Java]

![Spring]

![Angular]
![PostgreSQL]
![Docker]
![JUnit]
![Architecture]
</div>

---

# 📖 Descripción general:

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


# 👨‍💻 Autor

**Marcos Rojas Alvarez**

Desarrollador Backend Java | Spring Boot
