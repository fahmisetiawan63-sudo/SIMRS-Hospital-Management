package com.otten32.core.patient.service;

import com.otten32.core.patient.dto.CreatePatientRequest;
import com.otten32.core.patient.dto.PatientDTO;
import com.otten32.core.patient.dto.UpdatePatientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPatientService {
    
    PatientDTO createPatient(CreatePatientRequest request);
    
    PatientDTO getPatientById(Long id);
    
    PatientDTO getPatientByMedicalRecordNo(String medicalRecordNo);
    
    PatientDTO getPatientByNik(String nik);
    
    Page<PatientDTO> searchPatients(String searchTerm, Pageable pageable);
    
    Page<PatientDTO> getPatientsByStatus(String status, Pageable pageable);
    
    PatientDTO updatePatient(Long id, UpdatePatientRequest request);
    
    void deactivatePatient(Long id);
    
    Page<PatientDTO> getAllPatients(Pageable pageable);
}
