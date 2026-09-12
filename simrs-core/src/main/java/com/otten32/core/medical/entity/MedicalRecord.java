package com.otten32.core.medical.entity;

import com.otten32.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord extends BaseEntity {
    
    @Column(name = "patient_id", nullable = false)
    private Long patientId;
    
    @Column(name = "visit_date")
    private LocalDateTime visitDate;
    
    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis;
    
    @Column(name = "treatment", columnDefinition = "TEXT")
    private String treatment;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "status", length = 20)
    private String status = "ACTIVE";
}
