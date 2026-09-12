package com.otten32.core.radiology.dto;

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
public class RadiologyRequestDTO extends BaseDTO {
    private Long patientId;
    private String examinationType;
    private String status;
    private LocalDateTime requestDate;
    private String imagePath;
    private String findings;
    private Long verifiedBy;
    private LocalDateTime verifiedDate;
}
