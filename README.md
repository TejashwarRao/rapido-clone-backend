# Rapido Clone Backend - Auth Service

## Project Overview
This project is part of a Rapido-like ride booking backend system built using Spring Boot Microservices.

Current service implemented:
- Auth Service

---

## Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Maven
- Docker
- Spring Security
- REST APIs

---

## Features Implemented

- Spring Boot project setup
- PostgreSQL database connection
- REST Health API
- Docker setup
- Spring Security configuration
- Maven project structure
- GitHub integration

---

## Project Structure

```text
src/main/java/com/rapido/auth_service/

├── controller
├── config
├── service
├── repository
├── entity
├── dto
├── security
├── exception
└── util
```

---

## API Endpoint

### Health Check API

```http
GET /health
```

### Response

```text
Service Running Successfully
```

---

## Run Project

```bash
mvn clean install

mvn spring-boot:run
```

---

## Database Configuration

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rapido_db
spring.datasource.username=rapido_user
spring.datasource.password=rapido_pass
```

---

## Author

Tejashwar Rao
