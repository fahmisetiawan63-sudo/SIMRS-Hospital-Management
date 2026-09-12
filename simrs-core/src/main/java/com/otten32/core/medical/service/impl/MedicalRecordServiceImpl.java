package com.otten32.core.medical.service.impl;

import com.otten32.common.exception.ResourceNotFoundException;
import com.otten32.core.medical.dto.CreateMedicalRecordRequest;
import com.otten32.core.medical.dto.MedicalRecordDTO;
import com.otten32.core.medical.entity.MedicalRecord;
import com.otten32.core.medical.repository.MedicalRecordRepository;
import com.otten32.core.medical.service.IMedicalRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalRecordServiceImpl implements IMedicalRecordService {
    
    private final MedicalRecordRepository medicalRecordRepository;
    
    @Override
    @Transactional
    public MedicalRecordDTO createMedicalRecord(CreateMedicalRecordRequest request) {
        log.info("Creating medical record for patient ID: {}", request.getPatientId());
        
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .patientId(request.getPatientId())
                .visitDate(request.getVisitDate())
                .diagnosis(request.getDiagnosis())
                .treatment(request.getTreatment())
                .notes(request.getNotes())
                .status("ACTIVE")
                .build();
        
        MedicalRecord saved = medicalRecordRepository.save(medicalRecord);
        log.info("Medical record created with ID: {}", saved.getId());
        
        return mapToDTO(saved);
    }
    
    @Override
    @Transactional(readOnly = true)
    public MedicalRecordDTO getMedicalRecordById(Long id) {
        log.info("Fetching medical record with ID: {}", id);
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record", "id", id));
        return mapToDTO(medicalRecord);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<MedicalRecordDTO> getMedicalRecordsByPatientId(Long patientId, Pageable pageable) {
        log.info("Fetching medical records for patient ID: {}", patientId);
        Page<MedicalRecord> records = medicalRecordRepository.findByPatientId(patientId, pageable);
        return records.map(this::mapToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordDTO> getPatientMedicalHistory(Long patientId) {
        log.info("Fetching medical history for patient ID: {}", patientId);
        List<MedicalRecord> records = medicalRecordRepository.findByPatientIdOrderByVisitDateDesc(patientId);
        return records.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<MedicalRecordDTO> getMedicalRecordsByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        log.info("Fetching medical records between {} and {}", startDate, endDate);
        Page<MedicalRecord> records = medicalRecordRepository.findByVisitDateBetween(startDate, endDate, pageable);
        return records.map(this::mapToDTO);
    }
    
    @Override
    @Transactional
    public MedicalRecordDTO updateMedicalRecord(Long id, CreateMedicalRecordRequest request) {
        log.info("Updating medical record with ID: {}", id);
        
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record", "id", id));
        
        medicalRecord.setVisitDate(request.getVisitDate());
        medicalRecord.setDiagnosis(request.getDiagnosis());
        medicalRecord.setTreatment(request.getTreatment());
        medicalRecord.setNotes(request.getNotes());
        
        MedicalRecord updated = medicalRecordRepository.save(medicalRecord);
        log.info("Medical record updated with ID: {}", id);
        
        return mapToDTO(updated);
    }
    
    @Override
    @Transactional
    public void deleteMedicalRecord(Long id) {
        log.info("Deleting medical record with ID: {}", id);
        
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record", "id", id));
        
        medicalRecordRepository.delete(medicalRecord);
        log.info("Medical record deleted with ID: {}", id);
    }
    
    private MedicalRecordDTO mapToDTO(MedicalRecord medicalRecord) {
        return MedicalRecordDTO.builder()
                .id(medicalRecord.getId())
                .patientId(medicalRecord.getPatientId())
                .visitDate(medicalRecord.getVisitDate())
                .diagnosis(medicalRecord.getDiagnosis())
                .treatment(medicalRecord.getTreatment())
                .notes(medicalRecord.getNotes())
                .status(medicalRecord.getStatus())
                .createdAt(medicalRecord.getCreatedAt())
                .updatedAt(medicalRecord.getUpdatedAt())
                .createdBy(medicalRecord.getCreatedBy())
                .updatedBy(medicalRecord.getUpdatedBy())
                .build();
    }
}
