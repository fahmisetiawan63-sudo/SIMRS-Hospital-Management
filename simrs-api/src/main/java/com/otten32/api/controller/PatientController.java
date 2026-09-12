package com.otten32.api.controller;

import com.otten32.common.constant.AppConstants;
import com.otten32.common.dto.ApiResponse;
import com.otten32.common.dto.PaginationDTO;
import com.otten32.core.patient.dto.CreatePatientRequest;
import com.otten32.core.patient.dto.PatientDTO;
import com.otten32.core.patient.dto.UpdatePatientRequest;
import com.otten32.core.patient.service.IPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_BASE_URL + "/patients")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Patient Management", description = "APIs for managing patient information")
public class PatientController {

    private final IPatientService patientService;

    @PostMapping
    @Operation(summary = "Register a new patient", description = "Create a new patient record with basic information")
    public ResponseEntity<ApiResponse<PatientDTO>> createPatient(
            @Valid @RequestBody CreatePatientRequest request) {
        log.info("Creating new patient: {}", request.getFullName());
        PatientDTO patient = patientService.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(patient, "Patient registered successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Retrieve detailed information of a patient by their ID")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientById(
            @PathVariable Long id) {
        log.info("Fetching patient with ID: {}", id);
        PatientDTO patient = patientService.getPatientById(id);
        return ResponseEntity.ok(ApiResponse.success(patient));
    }

    @GetMapping("/medical-record/{medicalRecordNo}")
    @Operation(summary = "Get patient by medical record number", description = "Retrieve patient information using medical record number")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientByMedicalRecordNo(
            @PathVariable String medicalRecordNo) {
        log.info("Fetching patient with medical record no: {}", medicalRecordNo);
        PatientDTO patient = patientService.getPatientByMedicalRecordNo(medicalRecordNo);
        return ResponseEntity.ok(ApiResponse.success(patient));
    }

    @GetMapping("/nik/{nik}")
    @Operation(summary = "Get patient by NIK", description = "Retrieve patient information using NIK (National ID)")
    public ResponseEntity<ApiResponse<PatientDTO>> getPatientByNik(
            @PathVariable String nik) {
        log.info("Fetching patient with NIK: {}", nik);
        PatientDTO patient = patientService.getPatientByNik(nik);
        return ResponseEntity.ok(ApiResponse.success(patient));
    }

    @GetMapping("/search")
    @Operation(summary = "Search patients", description = "Search patients by name, NIK, or medical record number")
    public ResponseEntity<ApiResponse<PaginationDTO<PatientDTO>>> searchPatients(
            @RequestParam(value = "q", defaultValue = "") String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sortBy,
            @RequestParam(value = "order", defaultValue = "DESC") Sort.Direction order) {
        log.info("Searching patients with term: {}", searchTerm);
        Pageable pageable = PageRequest.of(page, Math.min(size, AppConstants.MAX_PAGE_SIZE), Sort.by(order, sortBy));
        Page<PatientDTO> patients = patientService.searchPatients(searchTerm, pageable);
        PaginationDTO<PatientDTO> paginationDTO = mapToPageDTO(patients);
        return ResponseEntity.ok(ApiResponse.success(paginationDTO));
    }

    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a paginated list of all patients")
    public ResponseEntity<ApiResponse<PaginationDTO<PatientDTO>>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "status", required = false) String status) {
        log.info("Fetching all patients");
        Pageable pageable = PageRequest.of(page, Math.min(size, AppConstants.MAX_PAGE_SIZE));
        
        Page<PatientDTO> patients;
        if (status != null && !status.isEmpty()) {
            patients = patientService.getPatientsByStatus(status, pageable);
        } else {
            patients = patientService.getAllPatients(pageable);
        }
        
        PaginationDTO<PatientDTO> paginationDTO = mapToPageDTO(patients);
        return ResponseEntity.ok(ApiResponse.success(paginationDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update patient information", description = "Update patient details")
    public ResponseEntity<ApiResponse<PatientDTO>> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePatientRequest request) {
        log.info("Updating patient with ID: {}", id);
        PatientDTO patient = patientService.updatePatient(id, request);
        return ResponseEntity.ok(ApiResponse.success(patient, "Patient updated successfully"));
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate patient", description = "Mark a patient as inactive")
    public ResponseEntity<ApiResponse<String>> deactivatePatient(
            @PathVariable Long id) {
        log.info("Deactivating patient with ID: {}", id);
        patientService.deactivatePatient(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Patient deactivated successfully"));
    }

    private <T> PaginationDTO<T> mapToPageDTO(Page<T> page) {
        return PaginationDTO.<T>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();
    }
}
