package com.otten32.core.laboratory.repository;

import com.otten32.core.laboratory.entity.LabRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRequestRepository extends JpaRepository<LabRequest, Long> {
    
    Page<LabRequest> findByPatientId(Long patientId, Pageable pageable);
    
    Page<LabRequest> findByStatus(String status, Pageable pageable);
    
    Page<LabRequest> findByTestType(String testType, Pageable pageable);
}
