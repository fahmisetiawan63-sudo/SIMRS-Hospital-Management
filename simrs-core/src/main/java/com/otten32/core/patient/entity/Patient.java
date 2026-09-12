package com.otten32.core.patient.entity;

import com.otten32.common.constant.AppConstants;
import com.otten32.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient extends BaseEntity {
    
    @Column(name = "medical_record_no", unique = true, nullable = false, length = 20)
    private String medicalRecordNo;
    
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;
    
    @Column(name = "nik", unique = true, length = 16)
    private String nik;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "gender", length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "blood_type", length = 5)
    private String bloodType;
    
    @Column(name = "phone", length = 15)
    private String phone;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
    
    @Column(name = "city", length = 50)
    private String city;
    
    @Column(name = "province", length = 50)
    private String province;
    
    @Column(name = "status", length = 20)
    private String status = AppConstants.PATIENT_STATUS_ACTIVE;
    
    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
