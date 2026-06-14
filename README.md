# 🛒 Spring Boot Ecommerce Web Application

A Spring Boot ecommerce demo project with JWT authentication, cart, orders, and admin panel.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot 3
- Spring Security (JWT Authentication)
- Spring Data JPA
- Swagger / OpenAPI
- PostgreSQL
- Docker
- JUnit 5 + Mockito + MockMvc

---

## ⚙️ How to Run the Project

### 🟢 Start Database (Docker)
```bash
docker compose up --build
```

### ▶️ Run Application
```bash
mvn spring-boot:run
```

---

## 📚 API Documentation (Swagger)

Once the application is running:

👉 http://localhost:8080/swagger-ui/index.html

From here you can:
- View all Product APIs
- Test endpoints directly from the browser
- Try secured endpoints with JWT authentication

---

## 🔐 JWT Authentication

This project uses JWT for security.

### Flow:
1. Login via `/auth/login`
2. Receive JWT token
3. Use token in protected requests:

```
Authorization: Bearer <token>
```

---

## 👤 Default Users

Created automatically on startup:

### ADMIN
- email: admin@test.com  
- password: admin  
- access: /admin

### USER
- email: user@test.com  
- password: user 
- access: /shop/products

---

## 🛒 Features

- Product listing (Shop API + UI)
- Product details
- Shopping cart with quantities
- Simulated checkout
- User orders
- Admin product panel
- Login / Register system
- Role-based access (USER / ADMIN)

---

## 🧪 Testing

The project includes some tests:

### Run tests
```bash
mvn test
```

---

## 🗄 Database

PostgreSQL connection:

```
jdbc:postgresql://localhost:5432/shopdb
```

Main tables:
- users
- products
- cart_items
- orders
- order_items

---

## 📦 Data Initialization

On startup, the application creates:

- Admin + User accounts
- 10 demo products

---

## 📌 Notes

- Swagger available at `/swagger-ui/index.html`
- JWT is required for protected endpoints
- Clean layered architecture: Controller → Service → Repository
