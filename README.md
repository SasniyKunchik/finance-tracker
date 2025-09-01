# Finance Tracker API

A backend service for managing personal financial data, built using modern Java technologies. This project demonstrates a well-structured Spring Boot application with persistence, messaging, and database migration practices suitable for scalable systems.

---

## Features

- **User, account, and transaction management** via RESTful API
- **Data persistence** with PostgreSQL and Spring Data JPA
- **Database schema management** using Liquibase
- **Event-driven communication** with Apache Kafka
- **DTO mapping** via MapStruct for clean data transfer
- **Input validation** using Jakarta Bean Validation
- **Containerization** with Docker for consistent deployment
- **Clean architecture** — layered design with separation of concerns

---

## 🛠 Technology Stack

| Component           | Technology                                  |
|---------------------|---------------------------------------------|
| Language            | Java 17+                                    |
| Framework           | Spring Boot 3                               |
| Persistence         | Spring Data JPA, PostgreSQL                 |
| Messaging           | Apache Kafka (Spring Kafka)                 |
| Database Migration  | Liquibase                                   |
| DTO Mapping         | MapStruct                                   |
| Validation          | Jakarta Bean Validation                     |
| Development Tools   | Lombok, Maven, Docker                       |

---

## Running the Application

### Prerequisites
- Java 17
- Docker (for PostgreSQL and Kafka)
