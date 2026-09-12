package com.otten32.core.patient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.otten32.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDTO extends BaseDTO {
    private String medicalRecordNo;
    private String fullName;
    private String nik;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodType;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String province;
    private String status;
}
