package com.otten32.core.patient.service.impl;

import com.otten32.common.exception.BusinessException;
import com.otten32.common.exception.ResourceNotFoundException;
import com.otten32.common.util.DateTimeUtil;
import com.otten32.core.patient.dto.CreatePatientRequest;
import com.otten32.core.patient.dto.PatientDTO;
import com.otten32.core.patient.dto.UpdatePatientRequest;
import com.otten32.core.patient.entity.Patient;
import com.otten32.core.patient.repository.PatientRepository;
import com.otten32.core.patient.service.IPatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements IPatientService {
    
    private final PatientRepository patientRepository;
    
    @Override
    @Transactional
    public PatientDTO createPatient(CreatePatientRequest request) {
        log.info("Creating new patient with NIK: {}", request.getNik());
        
        // Check if patient already exists
        if (patientRepository.findByNik(request.getNik()).isPresent()) {
            throw new BusinessException("Patient with NIK " + request.getNik() + " already exists");
        }
        
        if (patientRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException("Patient with email " + request.getEmail() + " already exists");
        }
        
        // Generate medical record number
        String medicalRecordNo = generateMedicalRecordNo();
        
        Patient patient = Patient.builder()
                .medicalRecordNo(medicalRecordNo)
                .fullName(request.getFullName())
                .nik(request.getNik())
                .dateOfBirth(request.getDateOfBirth())
                .gender(Patient.Gender.valueOf(request.getGender().toUpperCase()))
                .bloodType(request.getBloodType())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .city(request.getCity())
                .province(request.getProvince())
                .status("ACTIVE")
                .build();
        
        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient created successfully with ID: {}", savedPatient.getId());
        
        return mapToDTO(savedPatient);
    }
    
    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientById(Long id) {
        log.info("Fetching patient with ID: {}", id);
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        return mapToDTO(patient);
    }
    
    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientByMedicalRecordNo(String medicalRecordNo) {
        log.info("Fetching patient with medical record no: {}", medicalRecordNo);
        Patient patient = patientRepository.findByMedicalRecordNo(medicalRecordNo)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "medicalRecordNo", medicalRecordNo));
        return mapToDTO(patient);
    }
    
    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientByNik(String nik) {
        log.info("Fetching patient with NIK: {}", nik);
        Patient patient = patientRepository.findByNik(nik)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "nik", nik));
        return mapToDTO(patient);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<PatientDTO> searchPatients(String searchTerm, Pageable pageable) {
        log.info("Searching patients with term: {}", searchTerm);
        Page<Patient> patients = patientRepository.searchPatients(searchTerm, pageable);
        return patients.map(this::mapToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<PatientDTO> getPatientsByStatus(String status, Pageable pageable) {
        log.info("Fetching patients with status: {}", status);
        Page<Patient> patients = patientRepository.findByStatus(status, pageable);
        return patients.map(this::mapToDTO);
    }
    
    @Override
    @Transactional
    public PatientDTO updatePatient(Long id, UpdatePatientRequest request) {
        log.info("Updating patient with ID: {}", id);
        
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        
        if (request.getFullName() != null) {
            patient.setFullName(request.getFullName());
        }
        if (request.getDateOfBirth() != null) {
            patient.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getGender() != null) {
            patient.setGender(Patient.Gender.valueOf(request.getGender().toUpperCase()));
        }
        if (request.getBloodType() != null) {
            patient.setBloodType(request.getBloodType());
        }
        if (request.getPhone() != null) {
            patient.setPhone(request.getPhone());
        }
        if (request.getEmail() != null) {
            patient.setEmail(request.getEmail());
        }
        if (request.getAddress() != null) {
            patient.setAddress(request.getAddress());
        }
        if (request.getCity() != null) {
            patient.setCity(request.getCity());
        }
        if (request.getProvince() != null) {
            patient.setProvince(request.getProvince());
        }
        if (request.getStatus() != null) {
            patient.setStatus(request.getStatus());
        }
        
        Patient updatedPatient = patientRepository.save(patient);
        log.info("Patient updated successfully with ID: {}", id);
        
        return mapToDTO(updatedPatient);
    }
    
    @Override
    @Transactional
    public void deactivatePatient(Long id) {
        log.info("Deactivating patient with ID: {}", id);
        
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        
        patient.setStatus("INACTIVE");
        patientRepository.save(patient);
        
        log.info("Patient deactivated successfully with ID: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        log.info("Fetching all patients");
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(this::mapToDTO);
    }
    
    private String generateMedicalRecordNo() {
        return "MR-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private PatientDTO mapToDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .medicalRecordNo(patient.getMedicalRecordNo())
                .fullName(patient.getFullName())
                .nik(patient.getNik())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender().toString())
                .bloodType(patient.getBloodType())
                .phone(patient.getPhone())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .city(patient.getCity())
                .province(patient.getProvince())
                .status(patient.getStatus())
                .createdAt(patient.getCreatedAt())
                .updatedAt(patient.getUpdatedAt())
                .createdBy(patient.getCreatedBy())
                .updatedBy(patient.getUpdatedBy())
                .build();
    }
}
