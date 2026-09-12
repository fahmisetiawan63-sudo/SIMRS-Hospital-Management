# SIMRS Hospital Management System

Berhasil! Project SIMRS (Sistem Informasi Manajemen Rumah Sakit) telah disetup dengan lengkap.

## 📋 Ringkasan Setup

### ✅ Struktur Folder
```
SIMRS-Hospital-Management/
├── simrs-common/              # Shared utilities & constants
├── simrs-core/                # Core business logic
├── simrs-api/                 # REST API server
├── pom.xml                    # Parent POM
├── README.md                  # This file
└── .gitignore                 # Git ignore rules
```

### ✅ Modul yang Telah Diimplementasikan

1. **simrs-common** (Shared Module)
   - Base Entity & DTO classes
   - Common constants & enums
   - Exception handling (ResourceNotFoundException, BusinessException, ValidationException)
   - Utility classes (DateTimeUtil, ValidationUtil)
   - API Response wrapper class
   - Pagination DTO

2. **simrs-core** (Business Logic)
   - **Patient Management Module**
     - Patient entity dan repository
     - Patient service with business logic
     - Create/Update/Search functionality
   
   - **Medical Records Module**
     - MedicalRecord entity
     - Medical record service
     - Patient medical history tracking
   
   - **Pharmacy Module**
     - Medicine entity dan repository
     - Medicine inventory management
     - Expired medicine tracking
     - Low stock alerts
   
   - **Laboratory Module**
     - LabRequest entity
     - Lab request management
     - Test type tracking
   
   - **Billing Module**
     - Invoice entity
     - Invoice management
     - Revenue calculation
   
   - **Radiology Module**
     - RadiologyRequest entity
     - Radiology request management
     - Image path tracking

3. **simrs-api** (REST API)
   - Spring Boot application setup
   - PatientController dengan endpoints lengkap
   - Global exception handler
   - Swagger/OpenAPI configuration
   - Security configuration (BCrypt)
   - Database migration files (Flyway)
   - Application properties configuration

### 🔧 Technology Stack

- **Java 17 LTS**
- **Spring Boot 3.1.5**
- **Spring Data JPA** - ORM
- **Spring Security** - Authentication & Authorization
- **MySQL 8.0** - Database
- **Flyway** - Database migration
- **Lombok** - Code generation
- **Springdoc OpenAPI 2.0.4** - Swagger documentation
- **Maven 3.9** - Build tool

### 🚀 Cara Menjalankan

1. **Setup Database**
   ```bash
   # Create database
   CREATE DATABASE simrs_db;
   ```

2. **Konfigurasi Database** (di `simrs-api/src/main/resources/application.properties`)
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/simrs_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Build Project**
   ```bash
   cd SIMRS-Hospital-Management
   mvn clean install
   ```

4. **Run API Server**
   ```bash
   cd simrs-api
   mvn spring-boot:run
   ```

5. **Access API**
   - API Base URL: `http://localhost:8080/api/v1`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`
   - API Docs: `http://localhost:8080/api-docs`

### 📡 API Endpoints (Patient Management)

```
POST   /api/v1/patients              - Register pasien baru
GET    /api/v1/patients/{id}         - Get detail pasien
GET    /api/v1/patients              - Get all patients (paginated)
GET    /api/v1/patients/search       - Search patients
GET    /api/v1/patients/medical-record/{no} - Get by medical record no
GET    /api/v1/patients/nik/{nik}    - Get by NIK
PUT    /api/v1/patients/{id}         - Update patient
PUT    /api/v1/patients/{id}/deactivate - Deactivate patient
```

### 📝 Next Steps

- [ ] Implement Medical Records API Controller
- [ ] Implement Pharmacy API Controller
- [ ] Implement Laboratory API Controller
- [ ] Implement Billing API Controller
- [ ] Implement Radiology API Controller
- [ ] Add authentication/authorization (JWT)
- [ ] Add unit tests
- [ ] Add integration tests
- [ ] Setup CI/CD pipeline
- [ ] Create frontend application

### 📚 Database Schema

Database schema telah dikonfigurasi via Flyway migrations:
- `V1__Initial_Schema.sql` - Initial tables setup

Tabel yang tersedia:
- `patients` - Data pasien
- `staff` - Data karyawan
- `medical_records` - Rekam medis
- `medicines` - Data obat
- `lab_requests` - Permintaan lab
- `radiology_requests` - Permintaan radiologi
- `invoices` - Invoice penagihan

### 🔐 Security

- Password hashing dengan BCrypt
- Spring Security integration
- Ready for JWT token implementation
- SQL Injection prevention (Prepared statements)

### 📞 Support

- **Repository**: https://github.com/fahmisetiawan63-sudo/SIMRS-Hospital-Management
- **Issues**: GitHub Issues
- **Documentation**: See docs folder

---

**Status**: ✅ Project setup complete
**Version**: 1.0.0
**Last Updated**: September 12, 2026
