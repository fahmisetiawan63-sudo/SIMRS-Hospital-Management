# RANCANGAN SISTEM INFORMASI MANAJEMEN RUMAH SAKIT (SIMRS) OTTEN 32

## 1. OVERVIEW SISTEM

### 1.1 Informasi Umum
- **Nama Sistem:** SIMRS OTTEN 32
- **Tipe Rumah Sakit:** Medium (1000+ pasien/hari)
- **Bahasa Pemrograman:** Java
- **Framework:** Spring Boot 3.1.5
- **Database:** MySQL 8.0
- **Arsitektur:** Microservices (Modular)
- **Platform:** Web-Based (Browser)

### 1.2 Visi & Misi
- **Visi:** Menyediakan sistem informasi terintegrasi yang meningkatkan efisiensi operasional rumah sakit
- **Misi:** 
  - Digitalisasi penuh proses manajemen pasien
  - Integrasi data medis dan administratif
  - Peningkatan kualitas pelayanan kesehatan
  - Keamanan data dan privasi pasien

---

## 2. DEPARTEMEN & UNIT

### 2.1 Departemen Gawat Darurat dan Kritis
- **Unit Gawat Darurat (UGD/IGD)**
  - Manajemen pasien masuk darurat
  - Triage system
  - Monitoring vital signs
  - Rujukan pasien

- **Unit Perawatan Intensif (ICU)**
  - Monitoring pasien kritis
  - Laporan harian kondisi pasien
  - Manajemen perawatan intensif

### 2.2 Departemen Medis dan Spesialis
- Poliklinik berbagai spesialisasi
- Jadwal dokter spesialis
- Manajemen konsultasi

### 2.3 Unit Penunjang dan Administrasi
- **Radiologi:** Manajemen pemeriksaan radiologi, hasil imaging
- **Laboratorium:** Pemeriksaan lab, hasil tes, manajemen sampel
- **Apotek:** Inventori obat, resep, dispensing
- **Rawat Jalan:** Manajemen klinik, konsultasi
- **Rawat Inap:** Manajemen kamar, bed management

---

## 3. MODUL PRIORITAS & FITUR UTAMA

### 3.1 Modul Pendaftaran Pasien
**Fitur:**
- Registrasi pasien baru
- Verifikasi data pasien
- Update informasi pasien
- Asuransi/BPJS verification
- Riwayat pasien
- Medical record numbering

**Database Entities:**
- Patient
- PatientContact
- PatientInsurance
- PatientMedicalHistory

---

### 3.2 Modul EMR/EHR (Electronic Medical Record)
**Fitur:**
- Rekam medis digital pasien
- Riwayat diagnosa
- Resep digital
- Hasil pemeriksaan
- Catatan perawatan
- Discharge summary
- Akses multi-user (dokter, perawat, admin)

**Database Entities:**
- MedicalRecord
- Diagnosis
- Medication
- LabResult
- RadiologyResult
- ClinicalNotes
- VitalSigns

---

### 3.3 Modul Billing/Keuangan
**Fitur:**
- Invoice otomatis
- Manajemen pembayaran
- Verifikasi asuransi
- Laporan revenue
- Piutang pasien
- Rekonsiliasi keuangan
- Reporting keuangan

**Database Entities:**
- Invoice
- Payment
- InsuranceClaim
- BillingDetail
- FinancialReport

---

### 3.4 Modul Farmasi
**Fitur:**
- Manajemen inventori obat
- Pembelian obat (PO)
- Penerimaan barang
- Resep digital
- Dispensing obat
- Expired medication tracking
- Stok opname
- Laporan farmasi

**Database Entities:**
- Medicine
- MedicineStock
- PurchaseOrder
- Prescription
- Dispensing
- MedicineTransaction

---

### 3.5 Modul Laboratorium
**Fitur:**
- Permintaan pemeriksaan lab
- Manajemen sampel
- Input hasil lab
- Validasi hasil
- Laporan hasil ke pasien
- Manajemen peralatan lab
- Quality control

**Database Entities:**
- LabRequest
- LabSample
- LabResult
- LabParameter
- LabEquipment

---

### 3.6 Modul Radiologi
**Fitur:**
- Permintaan pemeriksaan radiologi
- Scheduling radiologi
- Upload hasil imaging
- Radiologist verification
- Report generation
- PACS integration ready
- Archive imaging

**Database Entities:**
- RadiologyRequest
- RadiologySchedule
- RadiologyResult
- ImagingData
- RadiologyReport

---

### 3.7 Modul Manajemen Staf
**Fitur:**
- Data karyawan
- Jadwal kerja/shift
- Izin & absensi
- Manajemen role & permission
- Sertifikasi staf
- Performance management

**Database Entities:**
- Staff
- StaffSchedule
- StaffLeave
- StaffRole
- StaffCertification

---

### 3.8 Modul Inventory
**Fitur:**
- Manajemen stok umum (non-farmasi)
- Pembelian barang
- Penerimaan barang
- Permintaan internal
- Stock level monitoring
- Supplier management
- Laporan inventory

**Database Entities:**
- InventoryItem
- InventoryStock
- InventoryTransaction
- Supplier
- PurchaseRequisition

---

## 4. ARSITEKTUR SISTEM

### 4.1 Layered Architecture
```
┌─────────────────────────────────────┐
│         PRESENTATION LAYER          │
│    (Web UI - HTML/CSS/JavaScript)   │
└─────────────────────────────────────┘
                   ↕
┌─────────────────────────────────────┐
│         API LAYER (REST)            │
│    Spring Boot Controllers          │
└─────────────────────────────────────┘
                   ↕
┌─────────────────────────────────────┐
│       BUSINESS LOGIC LAYER          │
│    Services & Business Rules        │
└─────────────────────────────────────┘
                   ↕
┌─────────────────────────────────────┐
│      DATA ACCESS LAYER (JPA)        │
│    Repositories & Entities          │
└───��─────────────────────────────────┘
                   ↕
┌─────────────────────────────────────┐
│         DATABASE LAYER              │
│    MySQL Database                   │
└─────────────────────────────────────┘
```

### 4.2 Module Structure
```
simrs-otten32/
├── simrs-common/              # Common utilities & constants
│   ├── src/main/java
│   │   └── com/otten32/common/
│   │       ├── dto/           # Shared DTOs
│   │       ├── entity/        # Base entities
│   │       ├── exception/     # Custom exceptions
│   │       ├── constant/      # Constants & enums
│   │       └── util/          # Utility classes
│   └── pom.xml
│
├── simrs-core/                # Core business logic
│   ├── src/main/java
│   │   └── com/otten32/core/
│   │       ├── patient/       # Patient management
│   │       ├── medical/       # Medical records
│   │       ├── billing/       # Billing module
│   │       ├── pharmacy/      # Pharmacy module
│   │       ├── laboratory/    # Laboratory module
│   │       ├── radiology/     # Radiology module
│   │       ├── staff/         # Staff management
│   │       └── inventory/     # Inventory module
│   └── pom.xml
│
├── simrs-api/                 # REST API & Web Service
│   ├── src/main/java
│   │   └── com/otten32/api/
│   │       ├── controller/    # REST Controllers
│   │       ├── config/        # Spring configuration
│   │       ├── security/      # Security config
│   │       └── Application.java
│   ├── src/main/resources
│   │   ├── application.properties
│   │   └── db/migration/      # Flyway migrations
│   ├── src/test/java
│   └── pom.xml
│
└── docs/                      # Documentation
    ├── DATABASE_SCHEMA.md
    ├── API_DOCUMENTATION.md
    └── DEPLOYMENT_GUIDE.md
```

---

## 5. DATABASE SCHEMA (Ringkas)

### 5.1 Core Tables

#### Patient (Pasien)
```sql
CREATE TABLE patients (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    medical_record_no VARCHAR(20) UNIQUE,
    full_name VARCHAR(100),
    nik VARCHAR(16),
    date_of_birth DATE,
    gender ENUM('MALE', 'FEMALE'),
    blood_type VARCHAR(5),
    phone VARCHAR(15),
    email VARCHAR(100),
    address TEXT,
    city VARCHAR(50),
    province VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### Medical Record (Rekam Medis)
```sql
CREATE TABLE medical_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    visit_date DATETIME,
    diagnosis TEXT,
    treatment TEXT,
    notes TEXT,
    created_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (created_by) REFERENCES staff(id)
);
```

#### Staff (Karyawan)
```sql
CREATE TABLE staff (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    position VARCHAR(50),
    department VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(15),
    hire_date DATE,
    status ENUM('ACTIVE', 'INACTIVE', 'LEAVE'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Pharmacy (Apotek)
```sql
CREATE TABLE medicines (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(20) UNIQUE,
    name VARCHAR(100),
    description TEXT,
    unit_price DECIMAL(10, 2),
    quantity INT,
    expiry_date DATE,
    supplier_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Laboratory (Laboratorium)
```sql
CREATE TABLE lab_requests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    test_type VARCHAR(50),
    status ENUM('PENDING', 'PROCESSING', 'COMPLETED'),
    request_date DATETIME,
    result TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);
```

#### Radiology (Radiologi)
```sql
CREATE TABLE radiology_requests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    examination_type VARCHAR(50),
    status ENUM('PENDING', 'COMPLETED'),
    request_date DATETIME,
    image_path VARCHAR(255),
    findings TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);
```

#### Billing (Penagihan)
```sql
CREATE TABLE invoices (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT,
    invoice_no VARCHAR(20) UNIQUE,
    total_amount DECIMAL(12, 2),
    paid_amount DECIMAL(12, 2),
    status ENUM('DRAFT', 'ISSUED', 'PAID', 'PARTIAL'),
    invoice_date DATETIME,
    due_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);
```

---

## 6. TECHNOLOGY STACK

### Backend
- **Framework:** Spring Boot 3.1.5
- **Java Version:** 17 LTS
- **Build Tool:** Maven 3.9
- **ORM:** Hibernate/JPA
- **Security:** Spring Security 6.x
- **API:** RESTful Web Services
- **Validation:** Bean Validation
- **Logging:** SLF4J + Logback

### Database
- **RDBMS:** MySQL 8.0+
- **Migration:** Flyway
- **Connection Pool:** HikariCP

### Frontend (Future Development)
- **Framework:** React.js / Angular
- **UI Library:** Bootstrap 5 / Material UI
- **State Management:** Redux / Context API
- **API Client:** Axios

### Development Tools
- **IDE:** IntelliJ IDEA / Eclipse
- **Version Control:** Git
- **CI/CD:** GitHub Actions / Jenkins
- **Testing:** JUnit 5, Mockito
- **API Documentation:** Swagger/OpenAPI 3.0

---

## 7. SECURITY CONSIDERATIONS

### 7.1 Authentication & Authorization
- Spring Security dengan JWT Token
- Role-Based Access Control (RBAC)
- Multi-factor authentication (future)

### 7.2 Data Protection
- Password hashing dengan BCrypt
- HTTPS/TLS encryption
- SQL Injection prevention (Prepared statements)
- CORS configuration
- Rate limiting

### 7.3 Audit Trail
- Logging semua aktivitas penting
- Tracking perubahan data medis
- Audit log per user action

### 7.4 Compliance
- Data privacy (GDPR-like requirements)
- Medical data confidentiality
- Backup & disaster recovery

---

## 8. API ENDPOINTS (Ringkas)

### Patient Management
```
POST   /api/v1/patients              - Register pasien baru
GET    /api/v1/patients/{id}         - Get detail pasien
PUT    /api/v1/patients/{id}         - Update data pasien
GET    /api/v1/patients/search        - Search pasien
```

### Medical Records
```
POST   /api/v1/medical-records       - Create rekam medis
GET    /api/v1/medical-records/{id}  - Get rekam medis
PUT    /api/v1/medical-records/{id}  - Update rekam medis
```

### Billing
```
POST   /api/v1/invoices              - Create invoice
GET    /api/v1/invoices/{id}         - Get invoice
POST   /api/v1/payments              - Record payment
```

### Pharmacy
```
GET    /api/v1/medicines             - List obat
POST   /api/v1/prescriptions         - Create resep
GET    /api/v1/medicines/stock       - Check stok obat
```

### Laboratory
```
POST   /api/v1/lab-requests          - Request lab test
GET    /api/v1/lab-results/{id}      - Get hasil lab
```

### Radiology
```
POST   /api/v1/radiology-requests    - Request radiologi
GET    /api/v1/radiology-results/{id}- Get hasil radiologi
```

---

## 9. DEPLOYMENT ARCHITECTURE

```
┌──────────────────────────────────────────────┐
│         CLIENTS (Browser)                    │
└──────────────────────┬───────────────────────┘
                       │
┌──────────────────────▼───────────────────────┐
│    NGINX Load Balancer / Reverse Proxy       │
└──────────────────────┬───────────────────────┘
                       │
        ┌──────────────┼──────────────┐
        │              │              │
┌───────▼────┐  ┌──────▼──────┐  ┌──▼───────┐
│ API Server │  │ API Server  │  │ API Svr  │
│ Instance 1 │  │ Instance 2  │  │Instance N│
└───────┬────┘  └──────┬──────┘  └──┬���──────┘
        │              │             │
        └──────────────┼─────────────┘
                       │
        ┌──────────────▼──────────────┐
        │   MySQL Database Cluster    │
        │  (Primary-Replica Setup)    │
        └─────────────────────────────┘
                       
        ┌──────────────────────────────┐
        │  File Storage / Media Server  │
        │  (Images, Lab Results, etc)   │
        └──────────────────────────────┘
```

---

## 10. DEVELOPMENT ROADMAP

### Phase 1 (Month 1-2): Foundation
- [x] Project setup & database schema
- [ ] Patient registration module
- [ ] Basic authentication
- [ ] Medical record basic structure

### Phase 2 (Month 3-4): Core Modules
- [ ] EMR/EHR complete
- [ ] Pharmacy module
- [ ] Laboratory module
- [ ] Radiology module

### Phase 3 (Month 5): Advanced Features
- [ ] Billing & payment system
- [ ] Reporting & analytics
- [ ] Staff management
- [ ] Inventory management

### Phase 4 (Month 6): Integration & Testing
- [ ] System integration testing
- [ ] Performance optimization
- [ ] Security audit
- [ ] User acceptance testing (UAT)

### Phase 5 (Ongoing): Maintenance & Enhancement
- [ ] Frontend development
- [ ] Mobile application
- [ ] Advanced analytics
- [ ] AI/ML integration (future)

---

## 11. QUICK START

```bash
# Clone repository
git clone https://github.com/fahmisetiawan63-sudo/SIMRS-Hospital-Management.git
cd SIMRS-Hospital-Management

# Build project
mvn clean install

# Run API server
cd simrs-api
mvn spring-boot:run

# Access API
http://localhost:8080/api/v1

# API Documentation (Swagger)
http://localhost:8080/swagger-ui.html
```

---

## 12. CONTACT & SUPPORT

- **Project Lead:** Fahmi Setiawan
- **Repository:** https://github.com/fahmisetiawan63-sudo/SIMRS-Hospital-Management
- **Issues & Discussion:** GitHub Issues
- **Documentation:** Wiki & Docs folder

---

**Last Updated:** September 2026
**Version:** 1.0.0
