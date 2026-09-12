package com.otten32.core.medical.repository;

import com.otten32.core.medical.entity.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    
    Page<MedicalRecord> findByPatientId(Long patientId, Pageable pageable);
    
    List<MedicalRecord> findByPatientIdOrderByVisitDateDesc(Long patientId);
    
    Page<MedicalRecord> findByVisitDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
