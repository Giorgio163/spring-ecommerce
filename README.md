# 🛒 Spring Boot Ecommerce API

A RESTful ecommerce application built with Spring Boot that demonstrates authentication, authorization, shopping cart management, checkout processing, order management, and product administration.

This project was created to strengthen backend development skills using modern Java and Spring technologies.

---

## 🚀 Features

### Authentication & Authorization

* JWT Authentication
* User Registration
* User Login
* Role-based authorization (USER / ADMIN)

### Product Management

* Create products
* Update products
* Delete products
* Product listing with pagination
* Product details endpoint

### Shopping Cart

* Add products to cart
* Remove products from cart
* Update quantities
* View current cart

### Orders

* Checkout process
* Order creation
* View user orders
* Order details

### API Documentation

* Swagger / OpenAPI integration
* Interactive endpoint testing

---

## 🏗 Architecture

The application follows a layered architecture:

Controller → Service → Repository → Database

Responsibilities:

* Controller: request handling and validation
* Service: business logic
* Repository: database access
* Security: JWT authentication and authorization

---

## 🛠 Tech Stack

* Java 21
* Spring Boot 3.5
* Spring Security
* JWT Authentication
* Spring Data JPA
* PostgreSQL
* Flyway Database Migrations
* Swagger / OpenAPI
* Docker
* JUnit 5
* Mockito
* MockMvc
* Maven

---

## ⚙️ Running the Project

### Start PostgreSQL

```bash
docker compose up -d
```

### Run Application

```bash
mvn spring-boot:run
```

Application:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Authentication

Login endpoint:

```http
POST /api/v1/auth/login
```

Example response:

```json
{
  "token": "jwt-token"
}
```

Use the token in protected requests:

```http
Authorization: Bearer <jwt-token>
```

---

## 👤 Demo Accounts

### Admin

Email:

```text
admin@test.com
```

Password:

```text
admin
```

Role:

```text
ADMIN
```

### User

Email:

```text
user@test.com
```

Password:

```text
user
```

Role:

```text
USER
```

---

## 🧪 Testing

The project contains:

### Controller Tests

* Product API
* Cart API
* Order API
* Checkout API
* Authentication API

### Service Tests

* ProductService

Technologies used:

* JUnit 5
* Mockito
* MockMvc

Run all tests:

```bash
mvn test
```

---

## 🗄 Database

PostgreSQL is used as the primary database.

Main entities:

* Users
* Products
* CartItems
* Orders
* OrderItems

Database migrations are managed through Flyway.

---

## 📦 Sample Data

At startup the application automatically creates:

* Admin account
* User account
* Demo products

This allows immediate testing without manual setup.

---

## 📚 Skills Demonstrated

* REST API Design
* Spring Security
* JWT Authentication
* Role-Based Access Control
* Dependency Injection
* JPA/Hibernate
* Database Migrations
* Unit Testing
* Controller Testing
* Docker Integration
* Clean Architecture

