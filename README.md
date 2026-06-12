# 🛒 Spring Boot Ecommerce Web Application

Spring Boot ecommerce demo con autenticazione, carrello, ordini e pannello admin.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Docker

---

## ⚙️ Avvio progetto

### 🟢 Docker (DB)

```bash
docker compose up --build
```

## 👤 Utenti default

Creati automaticamente all’avvio:

### ADMIN
- email: admin@test.com  
- password: admin  
- http://localhost:8080/admin


### USER
- email: user@test.com  
- password: user  

---

## 🛒 Funzionalità

- Lista prodotti (shop)
- Card UI prodotti
- Carrello con quantità
- Checkout simulato
- Ordini utente
- Admin panel prodotti
- Login
- Accesso per ruoli (USER / ADMIN)

---

## 🗄 Database

PostgreSQL:

jdbc:postgresql://localhost:5432/shopdb

Tabelle principali:
- users
- products
- cart_items
- orders
- order_items

---

## 📦 Data initialization

All’avvio vengono creati:

- Admin + User
- 10 prodotti demo