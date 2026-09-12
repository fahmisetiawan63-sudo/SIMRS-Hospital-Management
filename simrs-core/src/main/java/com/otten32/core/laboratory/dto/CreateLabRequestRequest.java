package com.otten32.core.laboratory.dto;

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
public class CreateLabRequestRequest {
    
    @NotNull(message = "Patient ID is required")
    private Long patientId;
    
    @NotBlank(message = "Test type is required")
    private String testType;
    
    @NotNull(message = "Request date is required")
    private LocalDateTime requestDate;
    
    private String notes;
}
