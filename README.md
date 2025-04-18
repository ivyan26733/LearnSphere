# 📘 LearnSphere — Microservices-Based Online Learning Platform

LearnSphere is a scalable, full-stack online learning platform built using **Spring Boot microservices**, designed to support students and instructors. It enables course creation, structured content delivery, student enrollment, and secure role-based access using JWT authentication.

---

## 🧱 Tech Stack

### 🌐 Backend
- **Spring Boot (3.x)** — REST APIs
- **Spring Cloud Gateway** — API Gateway & routing
- **Spring Security + JWT** — Role-based authentication & authorization
- **Spring Cloud Netflix Eureka** — Service discovery
- **Spring Cloud OpenFeign** — Inter-service communication
- **PostgreSQL** — Relational database for services
- **Lombok, JPA, ModelMapper** — Clean, maintainable code

### ☁️ Architecture
- Microservices architecture
- Each service uses a separate DB schema
- Services communicate via Feign Clients
- Stateless JWT authentication handled at Gateway level
- Docker (optional for containerization)

---

## 🚀 Microservices Breakdown

| Service         | Description |
|------------------|-------------|
| **API Gateway**  | Centralized routing, token validation, entry point for all requests |
| **Eureka Server**| Registers and locates all services |
| **Auth Service** | Handles user registration, login, JWT generation |
| **User Service** | Manages student/instructor profiles, separate entities with role-specific fields |
| **Course Service** | Course creation, modules, lessons, media uploads (in progress) |
| *(Future)* Enrollment Service | Track student enrollments and course access |
| *(Future)* Payment Service | For handling paid courses |
| *(Future)* Review Service | Course ratings and reviews |
| *(Future)* Notification Service | Email or in-app notifications |

---

## 🔐 Authentication & Security

- JWT-based stateless authentication
- `Authorization: Bearer <token>` header required in all protected routes
- Roles: `STUDENT`, `INSTRUCTOR`
- Gateway validates tokens before routing
- Microservices perform fine-grained role-based access using `@PreAuthorize`

---

## 🔁 Inter-Service Communication

- **Feign Clients** used for calling other services internally
- Example: Auth Service uses Feign to trigger profile creation in User Service post-registration

---

## 📁 Directory Structure

Each service follows this structure:

service-name ├── controller ├── service ├── repository ├── entity ├── dto ├── config ├── util └── application.properties


---

## 🛠️ Features Implemented

- ✅ User registration and login with hashed passwords
- ✅ JWT generation and validation
- ✅ Instructor and Student roles with distinct profile fields
- ✅ Inter-service communication using Feign
- ✅ Gateway token filtering and route management
- ✅ Profile creation auto-triggered from Auth to User Service
- ✅ Secure endpoints using `@PreAuthorize`
- ✅ Global exception handling and response standardization

---

## 📌 Services Overview

### 📍 Auth Service
- Handles login and registration
- Issues JWT containing email and role
- Communicates with User Service via Feign to auto-create profile

### 📍 User Service
- Manages two separate entities: `Student`, `Instructor`
- Fields like skills, social links, certifications, and image data
- Fetch logged-in user details via token
- Restricts sensitive operations using role and email checks

### 📍 Course Service (WIP)
- Handles:
    - Course CRUD
    - Modules and lessons
    - File uploads (videos, images)
- Role-specific actions (only instructors can create courses)

---

## 📬 API Routes

*(This section will be updated once all routes are finalized.)*

### Auth Service Route
- [POST] /auth/register
- [POST] /auth/login

### User Service Route
- [GET] /users/me
- [PUT] /users/me
- [GET] /users/instructors

### Course Service Route
- [POST] /course
- [POST] /course/{id}
- [GET] /courses














✅ **To be documented soon...**

---

## 🧪 Testing the Services

Use **Postman** or **cURL**:
1. Register/login a user via `/auth`
2. Use the JWT token returned
3. Send it in headers as `Authorization: Bearer <token>`
4. Access protected routes through `localhost:8080` (API Gateway)

---

## 💡 Future Enhancements

- Course reviews and ratings
- Enrollments and progress tracking
- Video streaming with watermarking
- Instructor dashboards and analytics
- Docker + Docker Compose deployment
- CI/CD pipeline

---

## 🤝 Contributing

This project is built for learning, exploration, and showcasing scalable architecture. Contributions, suggestions, and feature ideas are welcome.

---

