# CRUD API - Spring Boot

API REST desarrollada con Spring Boot para la gestión de productos, implementando operaciones CRUD completas mediante Spring Data JPA y MySQL.

## Tecnologías utilizadas

* Java 17
* Spring Boot 4
* Spring Data JPA
* Hibernate
* MySQL
* Maven

## Arquitectura

El proyecto sigue una arquitectura en capas (Controller, Service, Repository) con organización modular por entidad:

```text
Product
↓
    Controller
        ↓
    Service
        ↓
    Repository
        ↓
    Database
```

### Componentes

* **Entity:** Representa las tablas de la base de datos.
* **Repository:** Acceso a datos mediante Spring Data JPA.
* **Service:** Lógica de negocio.
* **Controller:** Exposición de endpoints REST.
* **Database:** Persistencia en MySQL.

## Estructura del proyecto

```text
src/main/java/com/marco/crudapi

├── Product
│   ├── Controller
│   │   └── ProductController.java
│   │
│   ├── Service
│   │   └── ProductService.java
│   │
│   ├── Repository
│   │   └── ProductRepository.java
│   │
│   └── Entity
│       └── Product.java
│
└── CrudapiApplication.java
```

## Configuración

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crud_spring
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8084
```

## Base de datos

### Tabla products

```sql
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);
```

### Datos de prueba

```sql
INSERT INTO products (name, price)
VALUES
('Laptop Lenovo', 3500.00),
('Monitor Samsung', 850.00);
```

## Endpoints

### Obtener todos los productos

```http
GET /api/products
```

Respuesta:

```json
[
  {
    "id": 1,
    "name": "Laptop Lenovo",
    "price": 3500.00
  }
]
```

---

### Obtener producto por ID

```http
GET /api/products/{id}
```

Respuesta:

```json
{
  "id": 1,
  "name": "Laptop Lenovo",
  "price": 3500.00
}
```

---

### Crear producto

```http
POST /api/products
```

Body:

```json
{
  "name": "Teclado Mecánico",
  "price": 250.00
}
```

Respuesta:

```http
201 Created
```

---

### Actualizar producto

```http
PUT /api/products/{id}
```

Body:

```json
{
  "name": "Teclado Mecánico RGB",
  "price": 300.00
}
```

Respuesta:

```http
200 OK
```

---

### Eliminar producto

```http
DELETE /api/products/{id}
```

Respuesta:

```http
204 No Content
```

## Manejo de respuestas HTTP

| Código | Descripción           |
| ------ | --------------------- |
| 200    | Operación exitosa     |
| 201    | Recurso creado        |
| 204    | Recurso eliminado     |
| 404    | Recurso no encontrado |

## Conceptos implementados

* Inyección de dependencias mediante constructor.
* Spring Data JPA.
* Uso de Optional para entidades opcionales.
* ResponseEntity para control de respuestas HTTP.
* Operaciones CRUD completas.
* Arquitectura en capas.
* Persistencia con Hibernate.

