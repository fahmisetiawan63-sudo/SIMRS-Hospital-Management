package com.otten32.core.radiology.repository;

import com.otten32.core.radiology.entity.RadiologyRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RadiologyRequestRepository extends JpaRepository<RadiologyRequest, Long> {
    
    Page<RadiologyRequest> findByPatientId(Long patientId, Pageable pageable);
    
    Page<RadiologyRequest> findByStatus(String status, Pageable pageable);
    
    Page<RadiologyRequest> findByExaminationType(String examinationType, Pageable pageable);
}
