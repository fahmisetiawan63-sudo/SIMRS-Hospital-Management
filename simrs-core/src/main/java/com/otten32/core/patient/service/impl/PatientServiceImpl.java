package com.otten32.core.patient.service.impl;

import com.otten32.common.exception.BusinessException;
import com.otten32.common.exception.ResourceNotFoundException;
import com.otten32.core.patient.dto.PatientDTO;
import com.otten32.core.patient.entity.Patient;
import com.otten32.core.patient.repository.PatientRepository;
import com.otten32.core.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementasi PatientService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientDTO registerPatient(PatientDTO patientDTO) {
        log.info("Registering new patient: {}", patientDTO.getFullName());

        // Check if patient already exists
        if (patientRepository.findByNik(patientDTO.getNik()).isPresent()) {
            throw new BusinessException("Pasien dengan NIK " + patientDTO.getNik() + " sudah terdaftar");
        }

        if (patientDTO.getEmail() != null && patientRepository.findByEmail(patientDTO.getEmail()).isPresent()) {
            throw new BusinessException("Email sudah terdaftar");
        }

        // Generate medical record number
        String medicalRecordNo = generateMedicalRecordNo();
        patientDTO.setMedicalRecordNo(medicalRecordNo);

        // Convert DTO to Entity
        Patient patient = Patient.builder()
                .fullName(patientDTO.getFullName())
                .nik(patientDTO.getNik())
                .dateOfBirth(patientDTO.getDateOfBirth())
                .gender(patientDTO.getGender())
                .bloodType(patientDTO.getBloodType())
                .phone(patientDTO.getPhone())
                .email(patientDTO.getEmail())
                .address(patientDTO.getAddress())
                .city(patientDTO.getCity())
                .province(patientDTO.getProvince())
                .zipCode(patientDTO.getZipCode())
                .medicalRecordNo(medicalRecordNo)
                .medicalHistory(patientDTO.getMedicalHistory())
                .allergies(patientDTO.getAllergies())
                .build();

        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient registered successfully with ID: {} and MR No: {}", savedPatient.getId(), medicalRecordNo);
        
        return convertToDTO(savedPatient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pasien dengan ID " + id + " tidak ditemukan"));
        return convertToDTO(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientByNik(String nik) {
        Patient patient = patientRepository.findByNik(nik)
                .orElseThrow(() -> new ResourceNotFoundException("Pasien dengan NIK " + nik + " tidak ditemukan"));
        return convertToDTO(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDTO getPatientByMedicalRecordNo(String medicalRecordNo) {
        Patient patient = patientRepository.findByMedicalRecordNo(medicalRecordNo)
                .orElseThrow(() -> new ResourceNotFoundException("Pasien dengan No. Rekam Medis " + medicalRecordNo + " tidak ditemukan"));
        return convertToDTO(patient);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        log.info("Updating patient with ID: {}", id);
        
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pasien dengan ID " + id + " tidak ditemukan"));

        // Update fields
        if (patientDTO.getFullName() != null) patient.setFullName(patientDTO.getFullName());
        if (patientDTO.getDateOfBirth() != null) patient.setDateOfBirth(patientDTO.getDateOfBirth());
        if (patientDTO.getGender() != null) patient.setGender(patientDTO.getGender());
        if (patientDTO.getBloodType() != null) patient.setBloodType(patientDTO.getBloodType());
        if (patientDTO.getPhone() != null) patient.setPhone(patientDTO.getPhone());
        if (patientDTO.getEmail() != null) patient.setEmail(patientDTO.getEmail());
        if (patientDTO.getAddress() != null) patient.setAddress(patientDTO.getAddress());
        if (patientDTO.getCity() != null) patient.setCity(patientDTO.getCity());
        if (patientDTO.getProvince() != null) patient.setProvince(patientDTO.getProvince());
        if (patientDTO.getZipCode() != null) patient.setZipCode(patientDTO.getZipCode());
        if (patientDTO.getMedicalHistory() != null) patient.setMedicalHistory(patientDTO.getMedicalHistory());
        if (patientDTO.getAllergies() != null) patient.setAllergies(patientDTO.getAllergies());
        if (patientDTO.getStatus() != null) patient.setStatus(patientDTO.getStatus());

        Patient updatedPatient = patientRepository.save(patient);
        log.info("Patient updated successfully with ID: {}", id);
        
        return convertToDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        log.info("Deleting patient with ID: {}", id);
        
        if (!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pasien dengan ID " + id + " tidak ditemukan");
        }
        
        patientRepository.deleteById(id);
        log.info("Patient deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> searchPatient(String keyword) {
        log.info("Searching patient with keyword: {}", keyword);
        List<Patient> patients = patientRepository.searchPatient(keyword);
        return patients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Generate medical record number (Format: MR-YYYYMMDD-XXXX)
     */
    private String generateMedicalRecordNo() {
        String prefix = "MR-" + java.time.LocalDate.now().toString().replace("-", "");
        long count = patientRepository.count() + 1;
        return prefix + "-" + String.format("%04d", count % 10000);
    }

    /**
     * Convert Patient entity to DTO
     */
    private PatientDTO convertToDTO(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .nik(patient.getNik())
                .dateOfBirth(patient.getDateOfBirth())
                .gender(patient.getGender())
                .bloodType(patient.getBloodType())
                .phone(patient.getPhone())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .city(patient.getCity())
                .province(patient.getProvince())
                .zipCode(patient.getZipCode())
                .medicalRecordNo(patient.getMedicalRecordNo())
                .medicalHistory(patient.getMedicalHistory())
                .allergies(patient.getAllergies())
                .status(patient.getStatus())
                .createdAt(patient.getCreatedAt())
                .updatedAt(patient.getUpdatedAt())
                .build();
    }
}
