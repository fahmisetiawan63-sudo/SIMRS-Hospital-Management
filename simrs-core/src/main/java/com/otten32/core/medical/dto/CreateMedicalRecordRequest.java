package com.otten32.core.medical.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateMedicalRecordRequest {
    
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    @NotNull(message = "Visit date is required")
    private LocalDateTime visitDate;
    
    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;
    
    @NotBlank(message = "Treatment is required")
    private String treatment;
    
    private String notes;
}
