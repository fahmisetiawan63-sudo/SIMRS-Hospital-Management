package com.otten32.core.medical.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otten32.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalRecordDTO extends BaseDTO {
    private Long patientId;
    private LocalDateTime visitDate;
    private String diagnosis;
    private String treatment;
    private String notes;
    private String status;
}
