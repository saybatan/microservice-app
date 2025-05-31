# 🧩 microservice-app

A distributed microservices application built with **Java 17** and **Spring Boot**, featuring **account management**, **ticket assignment**, and **asynchronous notification delivery** via **RabbitMQ**. The system is designed for scalability, observability, and maintainability using modern DevOps tools and patterns.

---

## 📁 Project Structure

```
microservice-app/
├── account-service/        # Manages user accounts
├── ticket-service/         # Handles ticket creation & assignment
├── notification-service/   # Sends notifications via RabbitMQ
├── eureka-server/          # Service discovery (Spring Cloud Eureka)
├── config-server/          # Centralized configuration management
├── api-gateway/            # Gateway for routing external/internal traffic
├── admin-server/           # Service monitoring via Spring Boot Admin
├── zipkin/                 # Distributed tracing
└── docker-compose.yml      # Infrastructure setup
```

---

## 🧰 Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Cloud (Eureka, Gateway, Config)**
- **Spring Security (if needed)**
- **Spring Data JPA & Cassandra**
- **RabbitMQ** (via Spring AMQP)
- **MySQL & Cassandra**
- **Docker & Docker Compose**
- **Zipkin** (Distributed Tracing)
- **Elasticsearch & Kibana** (Log monitoring)
- **Spring Boot Admin**
- **IntelliJ IDEA**
- **Lombok**, **MapStruct**, etc.

---

## 🔄 Workflow Overview

1. A user is created via `account-service`.
2. A ticket is created and assigned to the user via `ticket-service`.
3. A notification event is published to RabbitMQ.
4. `notification-service` consumes the message and notifies the user.

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Docker & Docker Desktop
- IntelliJ IDEA (recommended)

### Steps

1. **Clone the repository**

```bash
git clone https://github.com/yourusername/microservice-app.git
cd microservice-app
```

2. **Start infrastructure with Docker Compose**

```bash
docker-compose up -d
```

This will start:
- RabbitMQ
- MySQL
- Elasticsearch
- Kibana
- Zipkin
- Cassandra

3. **Start the microservices**

Start services in the following order (manually via IDE or `mvn spring-boot:run`):

```
1. config-server
2. eureka-server
3. api-gateway
4. account-service
5. ticket-service
6. notification-service
7. admin-server
```

---

## 🔐 Authentication (Optional)

> If using JWT or any other authentication mechanism, describe it here.

---

## 📬 Example APIs

| Service             | Endpoint                         | Description              |
|---------------------|----------------------------------|--------------------------|
| account-service     | `POST /api/accounts`             | Create user account      |
| ticket-service      | `POST /api/tickets`              | Create and assign ticket |
| notification-service| `POST /api/notifications`        | Trigger notification     |

---

## 📊 Monitoring & Dashboards

| Tool                | URL                                |
|---------------------|-------------------------------------|
| Eureka Dashboard     | http://localhost:8500              |
| Spring Boot Admin    | http://localhost:8081              |
| Kibana               | http://localhost:5601              |
| Zipkin               | http://localhost:9411              |
| RabbitMQ Dashboard   | http://localhost:15672 (guest/guest) |

---

## 💡 Notes

- Each service has its own `application.yml` managed centrally by `config-server`.
- Inter-service communication is handled using **REST** and **RabbitMQ**.
- Fault tolerance and resilience patterns can be added via **Resilience4j** (future scope).
- Monitoring, logging, and distributed tracing are integrated via Zipkin, Elasticsearch, and Kibana.

---

## 📄 License

This project is licensed under the MIT License.

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
