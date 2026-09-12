package com.otten32.core.patient.repository;

import com.otten32.core.patient.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    Optional<Patient> findByMedicalRecordNo(String medicalRecordNo);
    
    Optional<Patient> findByNik(String nik);
    
    Optional<Patient> findByEmail(String email);
    
    @Query("SELECT p FROM Patient p WHERE p.fullName LIKE CONCAT('%', :searchTerm, '%') OR p.nik LIKE CONCAT('%', :searchTerm, '%') OR p.medicalRecordNo LIKE CONCAT('%', :searchTerm, '%')")
    Page<Patient> searchPatients(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    Page<Patient> findByStatus(String status, Pageable pageable);
}
