# Safe Harbor - Ship Management System

A Spring Boot MVC RESTful API for managing ship information, security assessments, infractions, and harbor history.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Security Considerations](#security-considerations)
- [Future Enhancements](#future-enhancements)

## Overview

Safe Harbor is a comprehensive ship management system built as a RESTful API using Spring Boot. The system manages information about ships, their security assessments, infraction records, and harbor visit history. It provides a robust API for port authorities to track, monitor, and manage ships entering and leaving their harbors.

## Features

- **Ship Information Management**: Track ship details including registry information, ownership, and technical specifications.
- **Security Assessment Records**: Manage different types of security assessments with scoring and risk flagging.
- **Infraction Tracking**: Record and query ship infractions by type and date.
- **Harbor Visit History**: Track ship movements between ports with risk assessment capabilities.
- **Comprehensive Search**: Find ships and records by various criteria including registry numbers, flag state, and risk levels.
- **Swagger Documentation**: Auto-generated API documentation with interactive testing capability.
- **In-Memory Database**: Pre-configured H2 database with sample data for immediate testing.

## Technology Stack

- **Java 17**: Core programming language
- **Spring Boot 3.2.0**: Application framework
- **Spring Data JPA**: Data access layer
- **H2 Database**: In-memory database for development and testing
- **Lombok**: Reduces boilerplate code
- **Swagger/OpenAPI**: API documentation
- **Maven**: Build tool and dependency management

## Project Structure

The project follows a standard layered architecture:

```
safe-harbor/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── safeharbor/
│   │   │           ├── SafeHarborApplication.java (Main class)
│   │   │           ├── controller/                (REST controllers)
│   │   │           ├── model/                     (Entity classes)
│   │   │           ├── repository/                (Data access interfaces)
│   │   │           ├── service/                   (Business logic)
│   │   │           ├── dto/                       (Data Transfer Objects)
│   │   │           └── exception/                 (Exception handling)
│   │   └── resources/
│   │       ├── application.properties             (Application configuration)
│   │       ├── data.sql                           (Sample data)
│   │       └── schema.sql                         (Database schema)
│   └── test/
│       └── java/                                  (Test classes)
└── pom.xml                                        (Project dependencies)
```

## Database Schema

The application uses four main tables:

1. **ship_info**: Core ship information
2. **ssa_assessments**: Security and safety assessments
3. **infractions**: Documented rule violations
4. **harbor_history**: Port visit records

```sql
CREATE TABLE ship_info (
    file_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ship_name VARCHAR(100) NOT NULL,
    ship_flag VARCHAR(30),
    ship_type VARCHAR(50),
    port_of_registry VARCHAR(70),
    working_languages VARCHAR(100),
    official_registry_number BIGINT UNIQUE,
    call_sign VARCHAR(20),
    ship_owner VARCHAR(100),
    class VARCHAR(100),
    cargo_capacity_tonnage FLOAT,
    date_of_ssa DATE,
    ssa_by VARCHAR(100),
    is_high_risk_crew BOOLEAN DEFAULT FALSE,
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    record_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ship_status VARCHAR(30)
);

CREATE TABLE ssa_assessments (
    ssa_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    file_id BIGINT NOT NULL,
    ssa_type VARCHAR(20) CHECK (ssa_type IN ('violence', 'health', 'cyber', 'illegal', 'other')),
    ssa_score INT CHECK (ssa_score BETWEEN 0 AND 100),
    ssa_comments VARCHAR(255),
    is_suspect BOOLEAN DEFAULT FALSE,
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (file_id) REFERENCES ship_info(file_id) ON DELETE CASCADE
);

CREATE TABLE infractions (
    infraction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    file_id BIGINT NOT NULL,
    infraction_type VARCHAR(20) CHECK (infraction_type IN ('documentation_issue', 'physical_dmg', 'custom')),
    details TEXT,
    infraction_date DATE,
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (file_id) REFERENCES ship_info(file_id) ON DELETE CASCADE
);

CREATE TABLE harbor_history (
    history_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    file_id BIGINT NOT NULL,
    port_visited VARCHAR(100) NOT NULL,
    country VARCHAR(50),
    arrival_date DATE NOT NULL,
    departure_date DATE DEFAULT NULL,
    is_high_risk BOOLEAN DEFAULT FALSE,
    risk_reason VARCHAR(20) CHECK (risk_reason IN ('disease', 'drugs', 'terrorism', 'smuggling', 'other')),
    record_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (file_id) REFERENCES ship_info(file_id) ON DELETE CASCADE
);
```

## API Endpoints

The application provides the following primary API endpoints:

### Ship Information

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/ships | Get all ships |
| GET    | /api/ships/{id} | Get ship by ID |
| GET    | /api/ships/registry/{number} | Get ship by registry number |
| GET    | /api/ships/status/{status} | Get ships by status |
| GET    | /api/ships/high-risk-crew | Get ships with high-risk crews |
| GET    | /api/ships/flag/{flag} | Get ships by flag state |
| GET    | /api/ships/type/{type} | Get ships by ship type |
| POST   | /api/ships | Create a new ship |
| PUT    | /api/ships/{id} | Update a ship |
| DELETE | /api/ships/{id} | Delete a ship |

### Security Assessments

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/assessments | Get all assessments |
| GET    | /api/assessments/{id} | Get assessment by ID |
| GET    | /api/assessments/ship/{fileId} | Get assessments by ship ID |
| GET    | /api/assessments/type/{type} | Get assessments by type |
| GET    | /api/assessments/suspect | Get suspect assessments |
| GET    | /api/assessments/score/{minScore} | Get assessments by minimum score |
| POST   | /api/assessments | Create a new assessment |
| PUT    | /api/assessments/{id} | Update an assessment |
| DELETE | /api/assessments/{id} | Delete an assessment |

### Infractions

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/infractions | Get all infractions |
| GET    | /api/infractions/{id} | Get infraction by ID |
| GET    | /api/infractions/ship/{fileId} | Get infractions by ship ID |
| GET    | /api/infractions/type/{type} | Get infractions by type |
| GET    | /api/infractions/date-range | Get infractions by date range |
| POST   | /api/infractions | Create a new infraction |
| PUT    | /api/infractions/{id} | Update an infraction |
| DELETE | /api/infractions/{id} | Delete an infraction |

### Harbor History

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/harbor-history | Get all harbor history records |
| GET    | /api/harbor-history/{id} | Get history by ID |
| GET    | /api/harbor-history/ship/{fileId} | Get history by ship ID |
| GET    | /api/harbor-history/port/{port} | Get history by port |
| GET    | /api/harbor-history/country/{country} | Get history by country |
| GET    | /api/harbor-history/high-risk | Get high-risk harbor visits |
| GET    | /api/harbor-history/currently-docked | Get currently docked ships |
| GET    | /api/harbor-history/arrived-after | Get ships arriving after a date |
| POST   | /api/harbor-history | Create a new harbor history record |
| PUT    | /api/harbor-history/{id} | Update a harbor history record |
| DELETE | /api/harbor-history/{id} | Delete a harbor history record |

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or later
- Maven 3.6 or later
- Git (optional)

### Installation

1. Clone the repository (or download the source code):
   ```bash
   git clone https://github.com/yourusername/safe-harbor.git
   cd safe-harbor
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

### Running the Application

1. Start the application:
   ```bash
   mvn spring-boot:run
   ```
   
2. The application will be available at:
   - Application: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - H2 Console: http://localhost:8080/h2-console

3. H2 Database connection details:
   - JDBC URL: `jdbc:h2:mem:safeharbor`
   - Username: `sa`
   - Password: `password`

## API Documentation

The API is documented using Swagger/OpenAPI. Once the application is running, you can access the interactive documentation at:

```
http://localhost:8080/swagger-ui.html
```

This provides a complete reference to all endpoints with the ability to test them directly from the browser.

## Testing

The project includes unit and integration tests. To run the tests:

```bash
mvn test
```

## Security Considerations

This implementation focuses on functionality and does not include security features. For production use, consider adding:

- Authentication and authorization using Spring Security
- HTTPS configuration
- Rate limiting
- Input validation and sanitization
- Audit logging

## Future Enhancements

Potential enhancements for future versions:

1. User authentication and role-based access control
2. Advanced search functionality with filtering
3. Reporting and analytics dashboard
4. Notification system for high-risk ships
5. Integration with external maritime databases
6. Mobile-friendly web interface
7. Document upload capability for ship certificates
8. PDF report generation

---

This project was developed as a fullstack bootcamp final project demonstrating Spring Boot MVC REST API implementation with JPA and H2 database integration.
