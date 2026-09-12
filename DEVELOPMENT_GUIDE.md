# Development Guide

## Project Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.9+
- MySQL 8.0+
- Git

### Clone Repository
```bash
git clone https://github.com/fahmisetiawan63-sudo/SIMRS-Hospital-Management.git
cd SIMRS-Hospital-Management
```

### Database Setup

1. Create MySQL Database:
```sql
CREATE DATABASE simrs_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Update database credentials in `simrs-api/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/simrs_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Build Project

```bash
# Clean and install all modules
mvn clean install

# Build specific module
mvn clean install -DskipTests -pl simrs-api
```

### Run Application

```bash
# Navigate to simrs-api directory
cd simrs-api

# Run with Maven
mvn spring-boot:run

# Or build and run JAR
mvn clean package -DskipTests
java -jar target/simrs-api-1.0.0.jar
```

### Access Application

- **REST API**: http://localhost:8080/api/v1
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Documentation**: http://localhost:8080/api-docs
- **Health Check**: http://localhost:8080/actuator/health

## Development Workflow

### Creating a New Feature

1. Create a new branch:
```bash
git checkout -b feature/new-feature
```

2. Create your feature in appropriate module:
   - DTOs in `dto/` package
   - Entities in `entity/` package
   - Repositories in `repository/` package
   - Services in `service/` package
   - Controllers in API module

3. Follow Spring Boot best practices:
   - Use dependency injection
   - Write business logic in services
   - Use repositories for data access
   - Add proper exception handling

4. Commit and push:
```bash
git add .
git commit -m "feat: description of your feature"
git push origin feature/new-feature
```

### Code Structure

```
simrs-core/
└── src/main/java/com/otten32/core/
    ├── patient/
    │   ├── entity/Patient.java
    │   ├── dto/PatientDTO.java
    │   ├── repository/PatientRepository.java
    │   └── service/IPatientService.java
    ├── medical/
    ├── pharmacy/
    ├── laboratory/
    ├── billing/
    └── radiology/

simrs-api/
└── src/main/java/com/otten32/api/
    ├── controller/PatientController.java
    ├── config/SwaggerConfig.java
    ├── exception/GlobalExceptionHandler.java
    └── SimrsApiApplication.java
```

## Testing

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
mvn verify
```

### Test Specific Module
```bash
mvn test -pl simrs-core
```

## Logging

Configured logging levels in `application.properties`:
- ROOT: INFO
- com.otten32: DEBUG
- org.springframework.web: DEBUG

## Database Migrations

Flyway is configured to automatically run migrations on startup.

Migration files location: `simrs-api/src/main/resources/db/migration/`

### Creating New Migration
1. Create new SQL file: `V2__New_Migration.sql`
2. Place in migration folder
3. Restart application

## Common Issues & Solutions

### Database Connection Error
- Check MySQL is running
- Verify database URL and credentials
- Ensure database exists

### Port Already in Use
- Change port in `application.properties`: `server.port=8081`
- Or kill process using port 8080

### Build Fails
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn clean install -U`
- Check Java version: `java -version`

## Contributing Guidelines

1. Follow Java naming conventions
2. Use meaningful commit messages
3. Add proper exception handling
4. Write clean, readable code
5. Add logging for important operations
6. Test your changes before committing

---

For more information, see README.md
