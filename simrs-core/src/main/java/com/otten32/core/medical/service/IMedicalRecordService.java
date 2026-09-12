package com.otten32.core.medical.service;

import com.otten32.core.medical.dto.CreateMedicalRecordRequest;
import com.otten32.core.medical.dto.MedicalRecordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IMedicalRecordService {
    
    MedicalRecordDTO createMedicalRecord(CreateMedicalRecordRequest request);
    
    MedicalRecordDTO getMedicalRecordById(Long id);
    
    Page<MedicalRecordDTO> getMedicalRecordsByPatientId(Long patientId, Pageable pageable);
    
    List<MedicalRecordDTO> getPatientMedicalHistory(Long patientId);
    
    Page<MedicalRecordDTO> getMedicalRecordsByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    
    MedicalRecordDTO updateMedicalRecord(Long id, CreateMedicalRecordRequest request);
    
    void deleteMedicalRecord(Long id);
}
